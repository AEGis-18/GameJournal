package agus.gamejournal.app.repository.gameDetails;

import agus.gamejournal.app.model.gameDetails.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends JpaRepository<Platform,Long> {
}
