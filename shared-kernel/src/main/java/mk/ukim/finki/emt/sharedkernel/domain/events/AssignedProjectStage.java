package mk.ukim.finki.emt.sharedkernel.domain.events;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;

@Getter
public class AssignedProjectStage extends DomainEvent {

    private String projectStageId;

    private String teamId;

    public AssignedProjectStage() {
    }

    public AssignedProjectStage(String topic) {
        super(TopicHolder.TOPIC_PROJECT_STAGE_ASSIGNED_TO);
    }

    public AssignedProjectStage(String projectStageId, String teamId) {
        super(TopicHolder.TOPIC_PROJECT_STAGE_ASSIGNED_TO);
        this.projectStageId = projectStageId;
        this.teamId = teamId;
    }
}
