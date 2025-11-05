package agus.gamelist.app.service;

import agus.gamelist.app.dto.GameCoverDTO;
import agus.gamelist.app.dto.GameCoverMapper;
import agus.gamelist.app.model.Game;
import agus.gamelist.app.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;

    public Optional<GameCoverDTO> findGameCover(Long id) {
        return gameRepository.findById(id)
                .map(GameCoverMapper.INSTANCE::gameToGameCoverDTO);

    }

    public Page<GameCoverDTO> findAllGamesCovers(Pageable pageable) {
        Page<Game> page=gameRepository.findAll(pageable);

        return page.map(GameCoverMapper.INSTANCE::gameToGameCoverDTO);
    }

    public Optional<Game> findGameById(Long id) {
        return gameRepository.findById(id);
    }
}
