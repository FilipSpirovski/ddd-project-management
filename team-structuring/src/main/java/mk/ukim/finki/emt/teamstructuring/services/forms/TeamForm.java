package mk.ukim.finki.emt.teamstructuring.services.forms;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
public class TeamForm {

    @NotEmpty
    @NotBlank
    @Size(max = 255)
    private String name;

    @Positive
    private int maximumSize;
}
