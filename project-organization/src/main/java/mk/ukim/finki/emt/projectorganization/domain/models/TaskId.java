package mk.ukim.finki.emt.projectorganization.domain.models;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

public class TaskId extends DomainObjectId {

    private TaskId() {
        super(TaskId.randomId(TaskId.class).getId());
    }

    public TaskId(@NonNull String uuid) {
        super(uuid);
    }

    public static TaskId of(String uuid) {
        return new TaskId(uuid);
    }
}
