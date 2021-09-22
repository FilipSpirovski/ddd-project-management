package mk.ukim.finki.emt.teamstructuring.domain.exceptions;

public class TeamIsFull extends RuntimeException {
    public TeamIsFull() {
        super("The team has reached its capacity.");
    }
}
