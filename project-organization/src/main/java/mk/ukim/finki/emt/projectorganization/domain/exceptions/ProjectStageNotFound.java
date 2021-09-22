package mk.ukim.finki.emt.projectorganization.domain.exceptions;

public class ProjectStageNotFound extends RuntimeException {
    public ProjectStageNotFound(String id) {
        super(String.format("The project stage with the provided id(%s) was not found.", id));
    }
}
