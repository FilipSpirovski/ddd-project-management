package mk.ukim.finki.emt.teamstructuring.xports.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.teamstructuring.domain.exceptions.EmployeeAlreadyInTeam;
import mk.ukim.finki.emt.teamstructuring.domain.exceptions.TeamIsFull;
import mk.ukim.finki.emt.teamstructuring.domain.exceptions.TeamNotFound;
import mk.ukim.finki.emt.teamstructuring.domain.models.Team;
import mk.ukim.finki.emt.teamstructuring.services.TeamService;
import mk.ukim.finki.emt.teamstructuring.services.forms.EmployeeForm;
import mk.ukim.finki.emt.teamstructuring.services.forms.TeamForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
@AllArgsConstructor
public class TeamStructuringApi {

    private final TeamService teamService;

    @GetMapping()
    public List<Team> getAllTeams() {
        return this.teamService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamDetails(@PathVariable String id) {
        try {
            return ResponseEntity.ok(this.teamService.findById(id));
        } catch (TeamNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Team> addTeam(@RequestBody TeamForm teamForm) {
        try {
            return ResponseEntity.ok(this.teamService.add(teamForm));
        } catch (ConstraintViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<Team> editTeam(@PathVariable String id, @RequestBody TeamForm teamForm) {
        try {
            return ResponseEntity.ok(this.teamService.edit(id, teamForm));
        } catch (TeamNotFound e) {
            return ResponseEntity.notFound().build();
        } catch (ConstraintViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Team> deleteTeam(@PathVariable String id) {
        try {
            return ResponseEntity.ok(this.teamService.delete(id));
        } catch (TeamNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/employees/new")
    public ResponseEntity<Team> addEmployee(@PathVariable String id, @RequestBody EmployeeForm employeeForm) {
        try {
            return ResponseEntity.ok(this.teamService.addEmployee(employeeForm));
        } catch (TeamNotFound e) {
            return ResponseEntity.notFound().build();
        } catch (EmployeeAlreadyInTeam | TeamIsFull | ConstraintViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/employees/{employeeId}/edit")
    public ResponseEntity<Team> editEmployee(@PathVariable String id, @PathVariable String employeeId, @RequestBody EmployeeForm employeeForm) {
        try {
            return ResponseEntity.ok(this.teamService.editEmployee(employeeId, employeeForm));
        } catch (TeamNotFound e) {
            return ResponseEntity.notFound().build();
        } catch (ConstraintViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/employees/{employeeId}/delete")
    public ResponseEntity<Team> deleteEmployee(@PathVariable String id, @PathVariable String employeeId) {
        try {
            return ResponseEntity.ok(this.teamService.deleteEmployee(id, employeeId));
        } catch (TeamNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }
}
