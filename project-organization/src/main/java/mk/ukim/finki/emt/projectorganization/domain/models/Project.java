package mk.ukim.finki.emt.projectorganization.domain.models;

import lombok.Getter;
import mk.ukim.finki.emt.projectorganization.domain.exceptions.ProjectStageAlreadyInProject;
import mk.ukim.finki.emt.projectorganization.domain.exceptions.ProjectStageNotFound;
import mk.ukim.finki.emt.projectorganization.domain.exceptions.TaskAlreadyInProjectStage;
import mk.ukim.finki.emt.projectorganization.domain.exceptions.TaskNotFound;
import mk.ukim.finki.emt.projectorganization.domain.valueobjects.Details;
import mk.ukim.finki.emt.projectorganization.domain.valueobjects.Timeframe;
import mk.ukim.finki.emt.projectorganization.services.forms.ProjectForm;
import mk.ukim.finki.emt.projectorganization.services.forms.ProjectStageForm;
import mk.ukim.finki.emt.projectorganization.services.forms.TaskForm;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "projects")
public class Project extends AbstractEntity<ProjectId> {

    private Details details;

    private Timeframe timeframe;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ProjectStage> projectStages;

    public Project() {
    }

    public Project(ProjectForm projectForm) {
        super(ProjectId.randomId(ProjectId.class));
        this.setValues(projectForm);
        this.projectStages = new HashSet<>();
    }

    public void updateDetails(ProjectForm projectForm) {
        this.setValues(projectForm);
    }

    public void updateProjectStageDetails(String projectStageId, ProjectStageForm projectStageForm) throws ProjectStageNotFound {
        ProjectStage projectStage = this.findProjectStageById(projectStageId);

        projectStage.updateDetails(projectStageForm);
    }

    public void addProjectStageToProject(ProjectStage projectStage) {
        if (!this.projectStages.add(projectStage)) {
            throw new ProjectStageAlreadyInProject(projectStage);
        }
    }

    public void removeProjectStageFromProject(String projectStageId) throws ProjectStageNotFound {
        ProjectStage projectStage = this.findProjectStageById(projectStageId);

        this.projectStages.remove(projectStage);
    }

    public void updateTaskDetails(String taskId, TaskForm taskForm) throws ProjectStageNotFound, TaskNotFound {
        ProjectStage projectStage = this.findProjectStageById(taskForm.getProjectStageId());

        projectStage.updateTaskDetails(taskId, taskForm);
    }

    public void addTaskToProjectStage(String projectStageId, Task task) throws ProjectStageNotFound, TaskAlreadyInProjectStage {
        ProjectStage projectStage = this.findProjectStageById(projectStageId);

        projectStage.addTaskToProjectStage(task);
    }

    public void removeTaskFromProjectStage(String projectStageId, String taskId) throws ProjectStageNotFound, TaskNotFound {
        ProjectStage projectStage = this.findProjectStageById(projectStageId);

        projectStage.removeTaskFromProjectStage(taskId);
    }

    private void setValues(ProjectForm projectForm) {
        this.details = new Details(projectForm.getName(), projectForm.getDescription(), projectForm.getCurrentStatus());
        this.timeframe = new Timeframe(projectForm.getStartDate(), projectForm.getEndDate());
    }

    private ProjectStage findProjectStageById(String projectStageId) {
        return this.projectStages.stream()
                .filter(e -> e.getId().getId().equals(projectStageId))
                .findFirst()
                .orElseThrow(() -> new ProjectStageNotFound(projectStageId));
    }
}
