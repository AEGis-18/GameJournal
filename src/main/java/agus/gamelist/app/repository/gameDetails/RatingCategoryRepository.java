package agus.gamelist.app.repository.gameDetails;

import agus.gamelist.app.model.gameDetails.RatingCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingCategoryRepository extends JpaRepository<RatingCategory,Long> {
}
