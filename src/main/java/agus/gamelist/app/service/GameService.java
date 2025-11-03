package agus.gamelist.app.service;

import agus.gamelist.app.dto.GameCoverDTO;
import agus.gamelist.app.dto.GameCoverMapper;
import agus.gamelist.app.model.Game;
import agus.gamelist.app.repository.GameRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;

    public Optional<GameCoverDTO> getGameCover(Long id) {
        return gameRepository.findById(id)
                .map(GameCoverMapper.INSTANCE::gameToGameCoverDTO);

    }

    public List<GameCoverDTO> getAllGamesCovers() {
        return gameRepository.findAll().stream()
                .map(GameCoverMapper.INSTANCE::gameToGameCoverDTO)
                .collect(Collectors.toList());
    }
}
