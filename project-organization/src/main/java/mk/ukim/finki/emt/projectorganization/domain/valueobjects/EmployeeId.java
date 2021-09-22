package mk.ukim.finki.emt.projectorganization.domain.valueobjects;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

import javax.persistence.Embeddable;

@Embeddable
public class EmployeeId extends DomainObjectId {

    private EmployeeId() {
        super(EmployeeId.randomId(EmployeeId.class).getId());
    }

    public EmployeeId(@NonNull String uuid) {
        super(uuid);
    }
}
