package mk.ukim.finki.emt.teamstructuring.services;

import mk.ukim.finki.emt.teamstructuring.domain.models.Team;
import mk.ukim.finki.emt.teamstructuring.services.forms.EmployeeForm;
import mk.ukim.finki.emt.teamstructuring.services.forms.TeamForm;

import java.util.List;

public interface TeamService {

    List<Team> findAll();

    Team findById(String id);

    Team add(TeamForm teamForm);

    Team edit(String id, TeamForm teamForm);

    Team delete(String id);

    Team addEmployee(EmployeeForm employeeForm);

    Team editEmployee(String employeeId, EmployeeForm employeeForm);

    Team deleteEmployee(String id, String employeeId);
}
