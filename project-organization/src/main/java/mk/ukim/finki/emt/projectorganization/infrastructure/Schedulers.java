package mk.ukim.finki.emt.projectorganization.infrastructure;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.projectorganization.domain.models.Task;
import mk.ukim.finki.emt.projectorganization.domain.repository.ProjectRepository;
import mk.ukim.finki.emt.sharedkernel.domain.events.TaskRemainingTime;
import mk.ukim.finki.emt.sharedkernel.infrastructure.DomainEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class Schedulers {

    private ProjectRepository projectRepository;

    private DomainEventPublisher domainEventPublisher;

    @Scheduled(cron = "0 0 * * * *")
    public void tasksRemainingTime() {
        List<Task> ongoingTasks = new ArrayList<>();

        this.projectRepository.findAll()
                .forEach(project -> project.getProjectStages()
                        .forEach(projectStage -> projectStage.getTasks()
                                .stream()
                                .filter(task -> task.getTimeframe().getEndDate().isAfter(LocalDateTime.now()))
                                .forEach(ongoingTasks::add)));

        ongoingTasks.forEach(task -> {
            long duration = Duration.between(
                    LocalDateTime.now(),
                    task.getTimeframe().getEndDate()
            ).toMillis();
            long hours = TimeUnit.MILLISECONDS.toHours(duration);
            long minutes = TimeUnit.MILLISECONDS.toMinutes(duration) % 60;
            long seconds = TimeUnit.MILLISECONDS.toSeconds(duration) % 60;

            this.domainEventPublisher.publish(
                    new TaskRemainingTime(task.getId().getId(), task.getEmployeeId(), hours, minutes, seconds)
            );
        });
    }
}
