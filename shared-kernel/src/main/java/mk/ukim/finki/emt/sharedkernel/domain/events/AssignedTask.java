package mk.ukim.finki.emt.sharedkernel.domain.events;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;

@Getter
public class AssignedTask extends DomainEvent {

    private String taskId;

    private String employeeId;

    public AssignedTask() {
    }

    public AssignedTask(String topic) {
        super(TopicHolder.TOPIC_TASK_ASSIGNED_TO);
    }

    public AssignedTask(String taskId, String employeeId) {
        super(TopicHolder.TOPIC_TASK_ASSIGNED_TO);
        this.taskId = taskId;
        this.employeeId = employeeId;
    }
}
