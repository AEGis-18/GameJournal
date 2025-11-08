package agus.gamejournal.app.repository;

import agus.gamejournal.app.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findBySlug(String slug);
}
