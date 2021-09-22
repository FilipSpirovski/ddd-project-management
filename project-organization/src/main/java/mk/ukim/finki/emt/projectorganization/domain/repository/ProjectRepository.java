package mk.ukim.finki.emt.projectorganization.domain.repository;

import mk.ukim.finki.emt.projectorganization.domain.models.Project;
import mk.ukim.finki.emt.projectorganization.domain.models.ProjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, ProjectId> {
}
