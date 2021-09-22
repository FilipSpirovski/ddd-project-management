package mk.ukim.finki.emt.projectorganization.domain.models;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

public class ProjectStageId extends DomainObjectId {

    private ProjectStageId() {
        super(ProjectStageId.randomId(ProjectStageId.class).getId());
    }

    public ProjectStageId(@NonNull String uuid) {
        super(uuid);
    }

    public static ProjectStageId of(String uuid) {
        return new ProjectStageId(uuid);
    }
}
