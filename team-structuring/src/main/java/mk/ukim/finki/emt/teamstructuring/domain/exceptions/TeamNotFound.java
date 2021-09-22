package mk.ukim.finki.emt.teamstructuring.domain.exceptions;

public class TeamNotFound extends RuntimeException {
    public TeamNotFound(String id) {
        super(String.format("The team with the provided id(%s) was not found.", id));
    }
}
