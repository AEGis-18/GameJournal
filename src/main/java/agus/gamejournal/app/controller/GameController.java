package agus.gamejournal.app.controller;

import agus.gamejournal.app.dto.GameCoverDTO;
import agus.gamejournal.app.dto.GameInfoDTO;
import agus.gamejournal.app.model.Game;
import agus.gamejournal.app.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/games/")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*",
        allowCredentials = "true", maxAge = 3600)
public class GameController {
    private final GameService gameService;

    @GetMapping(path = "/covers")
    public ResponseEntity<Page<GameCoverDTO>> getGamesCovers(@PageableDefault(size = 10) Pageable pageable){
        return ResponseEntity.ok().body(gameService.findAllGamesCovers(pageable));
    }


    @GetMapping(path = "/{gameSlug}")
    public ResponseEntity<?> getGameInfo(@PathVariable String gameSlug){
        Optional<GameInfoDTO> games=gameService.findGameBySlug(gameSlug);
        if(games.isPresent()){
            return ResponseEntity.ok().body(games.get());
        }
        return ResponseEntity.notFound().build();
    }
}
