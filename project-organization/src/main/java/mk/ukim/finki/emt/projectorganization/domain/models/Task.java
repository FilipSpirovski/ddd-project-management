package mk.ukim.finki.emt.projectorganization.domain.models;

import lombok.Getter;
import mk.ukim.finki.emt.projectorganization.domain.valueobjects.Details;
import mk.ukim.finki.emt.projectorganization.domain.valueobjects.EmployeeId;
import mk.ukim.finki.emt.projectorganization.domain.valueobjects.Timeframe;
import mk.ukim.finki.emt.projectorganization.services.forms.TaskForm;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "tasks")
public class Task extends AbstractEntity<TaskId> {

    private Details details;

    private Timeframe timeframe;

    private String employeeId;

    public Task() {
    }

    public Task(TaskForm taskForm) {
        super(TaskId.randomId(TaskId.class));
        this.employeeId = taskForm.getEmployeeId();
        this.setValues(taskForm);
    }

    public void updateDetails(TaskForm taskForm) {
        this.setValues(taskForm);
    }

    private void setValues(TaskForm taskForm) {
        this.details = new Details(taskForm.getName(), taskForm.getDescription(), taskForm.getCurrentStatus());
        this.timeframe = new Timeframe(taskForm.getStartDate(), taskForm.getEndDate());
    }
}
