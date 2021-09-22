package mk.ukim.finki.emt.projectorganization.domain.valueobjects;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

import javax.persistence.Embeddable;

@Embeddable
public class TeamId extends DomainObjectId {

    private TeamId() {
        super(TeamId.randomId(TeamId.class).getId());
    }

    public TeamId(@NonNull String uuid) {
        super(uuid);
    }
}
