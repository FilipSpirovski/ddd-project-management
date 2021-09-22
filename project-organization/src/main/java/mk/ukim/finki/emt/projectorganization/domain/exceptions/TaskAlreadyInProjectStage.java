package mk.ukim.finki.emt.projectorganization.domain.exceptions;

import mk.ukim.finki.emt.projectorganization.domain.models.Task;

public class TaskAlreadyInProjectStage extends RuntimeException {
    public TaskAlreadyInProjectStage(Task task) {
        super(String.format("The task with the provided id(%s) is already part of the project stage.", task.getId().getId()));
    }
}
