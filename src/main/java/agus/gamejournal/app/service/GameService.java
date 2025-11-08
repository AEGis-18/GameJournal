package agus.gamejournal.app.service;

import agus.gamejournal.app.dto.GameCoverDTO;
import agus.gamejournal.app.dto.GameCoverMapper;
import agus.gamejournal.app.dto.GameInfoDTO;
import agus.gamejournal.app.dto.GameInfoMapper;
import agus.gamejournal.app.model.Game;
import agus.gamejournal.app.repository.GameRepository;
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

    public Optional<GameInfoDTO> findGameBySlug(String slug) {
        return gameRepository.findBySlug(slug).map(GameInfoMapper.INSTANCE::gameToGameInfoDTO);

    }
}
