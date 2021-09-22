package mk.ukim.finki.emt.teamstructuring.domain.repository;

import mk.ukim.finki.emt.teamstructuring.domain.models.Team;
import mk.ukim.finki.emt.teamstructuring.domain.models.TeamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, TeamId> {
}
