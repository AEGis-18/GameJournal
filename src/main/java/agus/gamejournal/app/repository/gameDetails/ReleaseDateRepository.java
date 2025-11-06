package agus.gamejournal.app.repository.gameDetails;

import agus.gamejournal.app.model.gameDetails.ReleaseDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseDateRepository extends JpaRepository<ReleaseDate, Long> {
}
