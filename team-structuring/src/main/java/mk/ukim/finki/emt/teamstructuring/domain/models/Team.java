package mk.ukim.finki.emt.teamstructuring.domain.models;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.teamstructuring.domain.exceptions.EmployeeAlreadyInTeam;
import mk.ukim.finki.emt.teamstructuring.domain.exceptions.EmployeeNotFound;
import mk.ukim.finki.emt.teamstructuring.domain.exceptions.TeamIsFull;
import mk.ukim.finki.emt.teamstructuring.domain.valueobjects.Quantity;
import mk.ukim.finki.emt.teamstructuring.services.forms.EmployeeForm;
import mk.ukim.finki.emt.teamstructuring.services.forms.TeamForm;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "teams")
public class Team extends AbstractEntity<TeamId> {

    private String name;

    private Quantity maximumSize;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Employee> teamMembers;

    public Team() {
    }

    public Team(TeamForm teamForm) {
        super(TeamId.randomId(TeamId.class));
        this.setValues(teamForm);
        this.teamMembers = new HashSet<>();
    }

    public void updateTeamDetails(TeamForm teamForm) {
        this.setValues(teamForm);
    }

    public void updateEmployeeDetails(String employeeId, EmployeeForm employeeForm) throws EmployeeNotFound {
        Employee employee = this.findEmployeeById(employeeId);

        employee.updateDetails(employeeForm);
    }

    public void addEmployeeToTeam(Employee employee) {
        if (this.teamMembers.size() == this.maximumSize.getQuantity()) {
            throw new TeamIsFull();
        }

        if (!this.teamMembers.add(employee)) {
            throw new EmployeeAlreadyInTeam(employee);
        }
    }

    public void removeEmployeeFromTeam(String employeeId) throws EmployeeNotFound {
        Employee employee = this.findEmployeeById(employeeId);

        this.teamMembers.remove(employee);
    }

    private void setValues(TeamForm teamForm) {
        this.name = teamForm.getName();
        this.maximumSize = Quantity.valueOf(teamForm.getMaximumSize());
    }

    private Employee findEmployeeById(String employeeId) {
        return this.teamMembers.stream()
                .filter(e -> e.getId().getId().equals(employeeId))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFound(employeeId));
    }
}
