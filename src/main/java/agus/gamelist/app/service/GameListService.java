package agus.gamelist.app.service;

import agus.gamelist.app.dto.GameListGameRequest;
import agus.gamelist.app.model.Game;
import agus.gamelist.app.model.GameList;
import agus.gamelist.app.model.GameListGame;
import agus.gamelist.app.model.GameListGameId;
import agus.gamelist.app.repository.GameListGameRepository;
import agus.gamelist.app.repository.GameListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameListService {
    private final GameListRepository gameListRepository;
    private final GameService gameService;
    private final GameListGameRepository gameListGameRepository;

    public GameList createGameList() {
        GameList gameList = new GameList();
        gameList.setName("default");
        gameList.setDescription("default");
        gameList.setCreationDate(new Date());

        return gameListRepository.save(gameList);
    }

    public Optional<GameList> findById(Long gameListId) {
        return gameListRepository.findById(gameListId);
    }

    public GameListGame addGameToList(GameListGameRequest gameListGame) {
       Game game=
               gameService.findGameById(gameListGame.getGameId()).orElseThrow(()->new RuntimeException("Game not found"));
       GameList gameList=
               gameListRepository.findById(gameListGame.getGameListId()).orElseThrow(()->new RuntimeException("GameList not found"));
       GameListGameId gameListGameId=new GameListGameId(game.getId(), gameList.getId());
       GameListGame newGameListGame=new GameListGame();
       newGameListGame.setId(gameListGameId);
       newGameListGame.setGameList(gameList);
       newGameListGame.setGame(game);
       newGameListGame.setComment(gameListGame.getComment());
       newGameListGame.setScore(gameListGame.getScore());

       return gameListGameRepository.save(newGameListGame);
    }
}
