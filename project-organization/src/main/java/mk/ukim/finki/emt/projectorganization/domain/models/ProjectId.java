package mk.ukim.finki.emt.projectorganization.domain.models;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

public class ProjectId extends DomainObjectId {

    private ProjectId() {
        super(ProjectId.randomId(ProjectId.class).getId());
    }

    public ProjectId(@NonNull String uuid) {
        super(uuid);
    }

    public static ProjectId of(String uuid) {
        return new ProjectId(uuid);
    }
}
