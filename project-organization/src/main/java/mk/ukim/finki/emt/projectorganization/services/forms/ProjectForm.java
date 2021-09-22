package mk.ukim.finki.emt.projectorganization.services.forms;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ProjectForm {

    @NotEmpty
    @NotBlank
    @Size(max = 255)
    private String name;

    @NotEmpty
    @NotBlank
    @Size(max = 1000)
    private String description;

    @NotNull
    @Pattern(regexp = "ACTIVE|FINISHED")
    private String currentStatus;

    @NotEmpty
    @NotBlank
    private String startDate;

    @NotEmpty
    @NotBlank
    private String endDate;
}
