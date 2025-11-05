package agus.gamelist.app.repository;

import agus.gamelist.app.model.GameListGame;
import agus.gamelist.app.model.GameListGameId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameListGameRepository extends JpaRepository<GameListGame, GameListGameId>{

}
