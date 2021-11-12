package mk.ukim.finki.emt.sharedkernel.domain.events;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;

@Getter
public class TaskRemainingTime extends DomainEvent {

    private String taskId;

    private String employeeId;

    private long hours;

    private long minutes;

    private long seconds;

    public TaskRemainingTime() {
    }

    public TaskRemainingTime(String topic) {
        super(TopicHolder.TOPIC_TASK_REMAINING_TIME);
    }

    public TaskRemainingTime(String taskId, String employeeId, long hours, long minutes, long seconds) {
        super(TopicHolder.TOPIC_TASK_REMAINING_TIME);
        this.taskId = taskId;
        this.employeeId = employeeId;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }
}
