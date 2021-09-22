package mk.ukim.finki.emt.projectorganization.domain.valueobjects;

import lombok.Getter;
import mk.ukim.finki.emt.projectorganization.domain.models.Status;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Embeddable
public class Details implements ValueObject {

    private String name;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private Status currentStatus;

    public Details() {}

    public Details(String name, String description, String currentStatus) {
        this.name = name;
        this.description = description;
        this.currentStatus = Status.valueOf(currentStatus);
    }
}
