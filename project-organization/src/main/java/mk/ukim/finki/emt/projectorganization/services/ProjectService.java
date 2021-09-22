package mk.ukim.finki.emt.projectorganization.services;

import mk.ukim.finki.emt.projectorganization.domain.models.Project;
import mk.ukim.finki.emt.projectorganization.services.forms.ProjectForm;
import mk.ukim.finki.emt.projectorganization.services.forms.ProjectStageForm;
import mk.ukim.finki.emt.projectorganization.services.forms.TaskForm;

import java.util.List;

public interface ProjectService {

    List<Project> findAll();

    Project findById(String id);

    Project add(ProjectForm projectForm);

    Project edit(String id, ProjectForm projectForm);

    Project delete(String id);

    Project addProjectStage(ProjectStageForm projectStageForm);

    Project editProjectStage(String projectStageId, ProjectStageForm projectStageForm);

    Project deleteProjectStage(String id, String projectStageId);

    Project addTask(String id, TaskForm taskForm);

    Project editTask(String id, String taskId, TaskForm taskForm);

    Project deleteTask(String id, String projectStageId, String taskId);
}
