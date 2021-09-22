package mk.ukim.finki.emt.teamstructuring.domain.exceptions;

public class EmployeeNotFound extends RuntimeException {
    public EmployeeNotFound(String id) {
        super(String.format("The employee with the provided id(%s) was not found.", id));
    }
}
