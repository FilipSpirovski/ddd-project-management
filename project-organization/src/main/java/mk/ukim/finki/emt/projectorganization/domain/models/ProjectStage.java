package mk.ukim.finki.emt.projectorganization.domain.models;

import lombok.Getter;
import mk.ukim.finki.emt.projectorganization.domain.exceptions.ProjectStageNotFound;
import mk.ukim.finki.emt.projectorganization.domain.exceptions.TaskAlreadyInProjectStage;
import mk.ukim.finki.emt.projectorganization.domain.exceptions.TaskNotFound;
import mk.ukim.finki.emt.projectorganization.domain.valueobjects.Details;
import mk.ukim.finki.emt.projectorganization.domain.valueobjects.TeamId;
import mk.ukim.finki.emt.projectorganization.domain.valueobjects.Timeframe;
import mk.ukim.finki.emt.projectorganization.services.forms.ProjectStageForm;
import mk.ukim.finki.emt.projectorganization.services.forms.TaskForm;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "project_stages")
public class ProjectStage extends AbstractEntity<ProjectStageId> {

    private Details details;

    private Timeframe timeframe;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Task> tasks;

    private String teamId;

    public ProjectStage() {
    }

    public ProjectStage(ProjectStageForm projectStageForm) {
        super(ProjectStageId.randomId(ProjectStageId.class));
        this.teamId = projectStageForm.getTeamId();
        this.setValues(projectStageForm);
        this.tasks = new HashSet<>();
    }

    public void updateDetails(ProjectStageForm projectStageForm) {
        this.setValues(projectStageForm);
    }

    public void updateTaskDetails(String taskId, TaskForm taskForm) throws TaskNotFound {
        Task task = this.findTaskById(taskId);

        task.updateDetails(taskForm);
    }

    public void addTaskToProjectStage(Task task) {
        if (!this.tasks.add(task)) {
            throw new TaskAlreadyInProjectStage(task);
        }
    }

    public void removeTaskFromProjectStage(String taskId) throws TaskNotFound {
        Task task = this.findTaskById(taskId);

        this.tasks.remove(task);
    }

    private void setValues(ProjectStageForm projectStageForm) {
        this.details = new Details(projectStageForm.getName(), projectStageForm.getDescription(), projectStageForm.getCurrentStatus());
        this.timeframe = new Timeframe(projectStageForm.getStartDate(), projectStageForm.getEndDate());
    }

    private Task findTaskById(String taskId) {
        return this.tasks.stream()
                .filter(e -> e.getId().getId().equals(taskId))
                .findFirst()
                .orElseThrow(() -> new TaskNotFound(taskId));
    }
}
