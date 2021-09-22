package mk.ukim.finki.emt.teamstructuring.services.forms;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class EmployeeForm {

    @NotEmpty
    @NotBlank
    private String teamId;

    @NotEmpty
    @NotBlank
    @Size(max = 255)
    private String name;

    @NotEmpty
    @NotBlank
    @Size(max = 255)
    private String surname;

    @Positive
    private double amountOfSalary;

    @NotNull
    @Pattern(regexp = "MKD|USD|EUR")
    private String currencyOfSalary;

    @NotNull
    @Pattern(regexp = "FRONTEND_DEVELOPER|BACKEND_DEVELOPER|TEAM_LEAD|PROJECT_MANAGER")
    private String positionInTeam;
}
