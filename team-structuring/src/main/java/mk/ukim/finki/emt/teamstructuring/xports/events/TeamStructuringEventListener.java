package mk.ukim.finki.emt.teamstructuring.xports.events;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.AssignedProjectStage;
import mk.ukim.finki.emt.sharedkernel.domain.events.AssignedTask;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;
import mk.ukim.finki.emt.sharedkernel.domain.events.TaskRemainingTime;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class TeamStructuringEventListener {

    @KafkaListener(topics = TopicHolder.TOPIC_TASK_ASSIGNED_TO, groupId = "teamStructuring")
    public void consumeAssignedTaskEvent(String jsonMessage) {
        try {
            AssignedTask event = DomainEvent.fromJson(jsonMessage, AssignedTask.class);

            log.info(String.format(
                    "The new task with id %s has been assigned to the employee with id %s.",
                    event.getTaskId(), event.getEmployeeId()
            ));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @KafkaListener(topics = TopicHolder.TOPIC_PROJECT_STAGE_ASSIGNED_TO, groupId = "teamStructuring")
    public void consumeAssignedProjectStageEvent(String jsonMessage) {
        try {
            AssignedProjectStage event = DomainEvent.fromJson(jsonMessage, AssignedProjectStage.class);

            log.info(String.format(
                    "The new project stage with id %s has been assigned to the team with id %s.",
                    event.getProjectStageId(), event.getTeamId()
            ));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @KafkaListener(topics = TopicHolder.TOPIC_TASK_REMAINING_TIME, groupId = "teamStructuring")
    public void consumeTaskRemainingTimeEvent(String jsonMessage) {
        try {
            TaskRemainingTime event = DomainEvent.fromJson(jsonMessage, TaskRemainingTime.class);

            log.info(String.format(
                    "The remaining time for the task with id %s, assigned to the employee with id %s, " +
                            "is: %d hours, %d minutes and %d seconds.",
                    event.getTaskId(), event.getEmployeeId(), event.getHours(), event.getMinutes(), event.getSeconds()
            ));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
