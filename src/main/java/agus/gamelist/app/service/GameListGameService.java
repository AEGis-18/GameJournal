package agus.gamelist.app.service;

import agus.gamelist.app.repository.GameListGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameListGameService {
    private GameListGameRepository gameListGameRepository;
}
