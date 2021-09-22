package mk.ukim.finki.emt.teamstructuring.domain.models;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

public class TeamId extends DomainObjectId {

    private TeamId() {
        super(TeamId.randomId(TeamId.class).getId());
    }

    public TeamId(@NonNull String uuid) {
        super(uuid);
    }

    public static TeamId of(String uuid) {
        return new TeamId(uuid);
    }
}
