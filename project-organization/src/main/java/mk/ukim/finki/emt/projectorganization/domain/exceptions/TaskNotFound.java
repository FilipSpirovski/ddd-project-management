package mk.ukim.finki.emt.projectorganization.domain.exceptions;

public class TaskNotFound extends RuntimeException {
    public TaskNotFound(String id) {
        super(String.format("The task with the provided id(%s) was not found.", id));
    }
}
