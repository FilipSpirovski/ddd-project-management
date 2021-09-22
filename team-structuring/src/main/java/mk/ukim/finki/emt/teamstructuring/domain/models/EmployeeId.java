package mk.ukim.finki.emt.teamstructuring.domain.models;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

public class EmployeeId extends DomainObjectId {

    private EmployeeId() {
        super(EmployeeId.randomId(EmployeeId.class).getId());
    }

    public EmployeeId(@NonNull String uuid) {
        super(uuid);
    }

    public static EmployeeId of(String uuid) {
        return new EmployeeId(uuid);
    }
}
