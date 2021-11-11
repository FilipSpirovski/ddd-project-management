package mk.ukim.finki.emt.projectorganization.services.implementations;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.projectorganization.domain.exceptions.*;
import mk.ukim.finki.emt.projectorganization.domain.models.Project;
import mk.ukim.finki.emt.projectorganization.domain.models.ProjectId;
import mk.ukim.finki.emt.projectorganization.domain.models.ProjectStage;
import mk.ukim.finki.emt.projectorganization.domain.models.Task;
import mk.ukim.finki.emt.projectorganization.domain.repository.ProjectRepository;
import mk.ukim.finki.emt.projectorganization.services.ProjectService;
import mk.ukim.finki.emt.projectorganization.services.forms.ProjectForm;
import mk.ukim.finki.emt.projectorganization.services.forms.ProjectStageForm;
import mk.ukim.finki.emt.projectorganization.services.forms.TaskForm;
import mk.ukim.finki.emt.sharedkernel.domain.events.AssignedProjectStage;
import mk.ukim.finki.emt.sharedkernel.domain.events.AssignedTask;
import mk.ukim.finki.emt.sharedkernel.infrastructure.DomainEventPublisher;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final Validator validator;
    private final DomainEventPublisher domainEventPublisher;

    @Override
    public List<Project> findAll() {
        return this.projectRepository.findAll();
    }

    @Override
    public Project findById(String id) {
        return this.projectRepository.findById(ProjectId.of(id))
                .orElseThrow(() -> new ProjectNotFound(id));
    }

    @Override
    public Project add(ProjectForm projectForm) throws ConstraintViolationException {
        this.validateForm(projectForm);

        return this.projectRepository.save(new Project(projectForm));
    }

    @Override
    public Project edit(String id, ProjectForm projectForm) throws ProjectNotFound, ConstraintViolationException {
        this.validateForm(projectForm);
        Project project = this.findById(id);

        project.updateDetails(projectForm);

        return this.projectRepository.save(project);
    }

    @Override
    public Project delete(String id) throws ProjectNotFound {
        Project project = this.findById(id);

        this.projectRepository.delete(project);

        return project;
    }

    @Override
    public Project addProjectStage(ProjectStageForm projectStageForm) throws ProjectNotFound, ProjectStageAlreadyInProject,
            ConstraintViolationException {
        this.validateForm(projectStageForm);
        Project project = this.findById(projectStageForm.getProjectId());
        ProjectStage projectStage = new ProjectStage(projectStageForm);

        project.addProjectStageToProject(projectStage);
        this.domainEventPublisher.publish(
                new AssignedProjectStage(projectStage.getId().getId(), projectStageForm.getTeamId())
        );

        return this.projectRepository.save(project);
    }

    @Override
    public Project editProjectStage(String projectStageId, ProjectStageForm projectStageForm) throws ProjectNotFound,
            ProjectStageNotFound, ConstraintViolationException {
        this.validateForm(projectStageForm);
        Project project = this.findById(projectStageForm.getProjectId());

        project.updateProjectStageDetails(projectStageId, projectStageForm);

        return this.projectRepository.save(project);
    }

    @Override
    public Project deleteProjectStage(String id, String projectStageId) throws ProjectNotFound, ProjectStageNotFound {
        Project project = this.findById(id);

        project.removeProjectStageFromProject(projectStageId);

        return this.projectRepository.save(project);
    }

    @Override
    public Project addTask(String id, TaskForm taskForm) throws ProjectNotFound, ProjectStageNotFound, TaskAlreadyInProjectStage,
            ConstraintViolationException {
        this.validateForm(taskForm);
        Project project = this.findById(id);
        Task task = new Task(taskForm);

        project.addTaskToProjectStage(taskForm.getProjectStageId(), task);
        domainEventPublisher.publish(new AssignedTask(task.getId().getId(), taskForm.getEmployeeId()));

        return this.projectRepository.save(project);
    }

    @Override
    public Project editTask(String id, String taskId, TaskForm taskForm) throws ProjectNotFound, ProjectStageNotFound,
            TaskNotFound, ConstraintViolationException {
        this.validateForm(taskForm);
        Project project = this.findById(id);

        project.updateTaskDetails(taskId, taskForm);

        return this.projectRepository.save(project);
    }

    @Override
    public Project deleteTask(String id, String projectStageId, String taskId) throws ProjectNotFound, ProjectStageNotFound, TaskNotFound {
        Project project = this.findById(id);

        project.removeTaskFromProjectStage(projectStageId, taskId);

        return this.projectRepository.save(project);
    }

    private void validateForm(Object form) {
        Set<ConstraintViolation<Object>> constraintViolations = this.validator.validate(form);

        if (constraintViolations.size() > 0) {
            throw new ConstraintViolationException("The provided form is not valid.", constraintViolations);
        }
    }
}
