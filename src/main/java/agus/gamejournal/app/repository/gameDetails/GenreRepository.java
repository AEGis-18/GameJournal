package agus.gamejournal.app.repository.gameDetails;

import agus.gamejournal.app.model.gameDetails.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
