package mk.ukim.finki.emt.teamstructuring.services.implementations;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.teamstructuring.domain.exceptions.EmployeeAlreadyInTeam;
import mk.ukim.finki.emt.teamstructuring.domain.exceptions.TeamIsFull;
import mk.ukim.finki.emt.teamstructuring.domain.exceptions.TeamNotFound;
import mk.ukim.finki.emt.teamstructuring.domain.models.Employee;
import mk.ukim.finki.emt.teamstructuring.domain.models.Team;
import mk.ukim.finki.emt.teamstructuring.domain.models.TeamId;
import mk.ukim.finki.emt.teamstructuring.domain.repository.TeamRepository;
import mk.ukim.finki.emt.teamstructuring.services.TeamService;
import mk.ukim.finki.emt.teamstructuring.services.forms.EmployeeForm;
import mk.ukim.finki.emt.teamstructuring.services.forms.TeamForm;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final Validator validator;

    @Override
    public List<Team> findAll() {
        return this.teamRepository.findAll();
    }

    @Override
    public Team findById(String id) {
        return this.teamRepository.findById(TeamId.of(id))
                .orElseThrow(() -> new TeamNotFound(id));
    }

    @Override
    public Team add(TeamForm teamForm) throws ConstraintViolationException {
        this.validateForm(teamForm);

        return this.teamRepository.save(new Team(teamForm));
    }

    @Override
    public Team edit(String id, TeamForm teamForm) throws TeamNotFound, ConstraintViolationException {
        this.validateForm(teamForm);
        Team team = this.findById(id);

        team.updateTeamDetails(teamForm);

        return this.teamRepository.save(team);
    }

    @Override
    public Team delete(String id) throws TeamNotFound {
        Team team = this.findById(id);

        this.teamRepository.delete(team);

        return team;
    }

    @Override
    public Team addEmployee(EmployeeForm employeeForm) throws TeamNotFound, EmployeeAlreadyInTeam, TeamIsFull,
            ConstraintViolationException {
        this.validateForm(employeeForm);
        Team team = this.findById(employeeForm.getTeamId());

        team.addEmployeeToTeam(new Employee(employeeForm));

        return this.teamRepository.save(team);
    }

    @Override
    public Team editEmployee(String employeeId, EmployeeForm employeeForm) throws TeamNotFound, ConstraintViolationException {
        this.validateForm(employeeForm);
        Team team = this.findById(employeeForm.getTeamId());

        team.updateEmployeeDetails(employeeId, employeeForm);

        return this.teamRepository.save(team);
    }

    @Override
    public Team deleteEmployee(String id, String employeeId) throws TeamNotFound {
        Team team = this.findById(id);

        team.removeEmployeeFromTeam(employeeId);

        return this.teamRepository.save(team);
    }

    private void validateForm(Object form) {
        Set<ConstraintViolation<Object>> constraintViolations = this.validator.validate(form);

        if (constraintViolations.size() > 0) {
            throw new ConstraintViolationException("The provided form is not valid.", constraintViolations);
        }
    }
}
