package mk.ukim.finki.emt.teamstructuring.domain.exceptions;

import mk.ukim.finki.emt.teamstructuring.domain.models.Employee;

public class EmployeeAlreadyInTeam extends RuntimeException {
    public EmployeeAlreadyInTeam(Employee employee) {
        super(String.format("The employee with the provided id(%s) is already part of the team.", employee.getId().getId()));
    }
}
