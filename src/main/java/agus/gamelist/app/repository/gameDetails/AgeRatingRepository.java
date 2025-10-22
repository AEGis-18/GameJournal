package agus.gamelist.app.repository.gameDetails;

import agus.gamelist.app.model.gameDetails.AgeRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgeRatingRepository extends JpaRepository<AgeRating, Long> {
}
