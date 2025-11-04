package agus.gamelist.app.service;

import agus.gamelist.app.model.GameList;
import agus.gamelist.app.repository.GameListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameListService {
    final GameListRepository gameListRepository;

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
}
