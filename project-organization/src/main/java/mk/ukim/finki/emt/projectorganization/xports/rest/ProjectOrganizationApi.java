package mk.ukim.finki.emt.projectorganization.xports.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.projectorganization.domain.exceptions.*;
import mk.ukim.finki.emt.projectorganization.domain.models.Project;
import mk.ukim.finki.emt.projectorganization.services.ProjectService;
import mk.ukim.finki.emt.projectorganization.services.forms.ProjectForm;
import mk.ukim.finki.emt.projectorganization.services.forms.ProjectStageForm;
import mk.ukim.finki.emt.projectorganization.services.forms.TaskForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@AllArgsConstructor
public class ProjectOrganizationApi {

    private final ProjectService projectService;

    @GetMapping
    public List<Project> getAllProjects() {
        return this.projectService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectDetails(@PathVariable String id) {
        try {
            return ResponseEntity.ok(this.projectService.findById(id));
        } catch (ProjectNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Project> addProject(@RequestBody ProjectForm projectForm) {
        try {
            return ResponseEntity.ok(this.projectService.add(projectForm));
        } catch (ConstraintViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<Project> editProject(@PathVariable String id, @RequestBody ProjectForm projectForm) {
        try {
            return ResponseEntity.ok(this.projectService.edit(id, projectForm));
        } catch (ProjectNotFound e) {
            return ResponseEntity.notFound().build();
        } catch (ConstraintViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Project> deleteProject(@PathVariable String id) {
        try {
            return ResponseEntity.ok(this.projectService.delete(id));
        } catch (ProjectNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/project-stages/new")
    public ResponseEntity<Project> addProjectStage(@PathVariable String id, @RequestBody ProjectStageForm projectStageForm) {
        try {
            return ResponseEntity.ok(this.projectService.addProjectStage(projectStageForm));
        } catch (ProjectNotFound e) {
            return ResponseEntity.notFound().build();
        } catch (ProjectStageAlreadyInProject | ConstraintViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/project-stages/{projectStageId}/edit")
    public ResponseEntity<Project> editProjectStage(@PathVariable String id, @PathVariable String projectStageId, @RequestBody ProjectStageForm projectStageForm) {
        try {
            return ResponseEntity.ok(this.projectService.editProjectStage(projectStageId, projectStageForm));
        } catch (ProjectNotFound e) {
            return ResponseEntity.notFound().build();
        } catch (ConstraintViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}/project-stages/{projectStageId}/delete")
    public ResponseEntity<Project> deleteProjectStage(@PathVariable String id, @PathVariable String projectStageId) {
        try {
            return ResponseEntity.ok(this.projectService.deleteProjectStage(id, projectStageId));
        } catch (ProjectNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/project-stages/{projectStageId}/tasks/new")
    public ResponseEntity<Project> addTask(@PathVariable String id, @PathVariable String projectStageId, @RequestBody TaskForm taskForm) {
        try {
            return ResponseEntity.ok(this.projectService.addTask(id, taskForm));
        } catch (ProjectNotFound | ProjectStageNotFound e) {
            return ResponseEntity.notFound().build();
        } catch (TaskAlreadyInProjectStage | ConstraintViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/project-stages/{projectStageId}/tasks/{taskId}/edit")
    public ResponseEntity<Project> editTask(@PathVariable String id, @PathVariable String projectStageId, @PathVariable String taskId, @RequestBody TaskForm taskForm) {
        try {
            return ResponseEntity.ok(this.projectService.editTask(id, taskId, taskForm));
        } catch (ProjectNotFound | ProjectStageNotFound | TaskNotFound e) {
            return ResponseEntity.notFound().build();
        } catch (ConstraintViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}/project-stages/{projectStageId}/tasks/{taskId}/delete")
    public ResponseEntity<Project> deleteTask(@PathVariable String id, @PathVariable String projectStageId, @PathVariable String taskId) {
        try {
            return ResponseEntity.ok(this.projectService.deleteTask(id, projectStageId, taskId));
        } catch (ProjectNotFound | ProjectStageNotFound | TaskNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }
}
