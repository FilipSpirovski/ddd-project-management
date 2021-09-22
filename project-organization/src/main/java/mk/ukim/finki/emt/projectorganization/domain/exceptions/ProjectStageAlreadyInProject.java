package mk.ukim.finki.emt.projectorganization.domain.exceptions;

import mk.ukim.finki.emt.projectorganization.domain.models.ProjectStage;

public class ProjectStageAlreadyInProject extends RuntimeException {
    public ProjectStageAlreadyInProject(ProjectStage projectStage) {
        super(String.format("The project stage with the provided id(%s) is already part of the project.", projectStage.getId().getId()));
    }
}
