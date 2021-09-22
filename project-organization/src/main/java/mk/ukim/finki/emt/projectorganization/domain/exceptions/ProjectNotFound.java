package mk.ukim.finki.emt.projectorganization.domain.exceptions;

public class ProjectNotFound extends RuntimeException {
    public ProjectNotFound(String id) {
        super(String.format("The project with the provided id(%s) was not found.", id));
    }
}
