package agus.gamejournal.app.repository;

import agus.gamejournal.app.model.JournalGame;
import agus.gamejournal.app.model.JournalGameId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalGameRepository extends JpaRepository<JournalGame, JournalGameId>{

}
