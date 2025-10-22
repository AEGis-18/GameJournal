package agus.gamelist.app.repository.gameDetails;

import agus.gamelist.app.model.gameDetails.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends JpaRepository<Platform,Long> {
}
