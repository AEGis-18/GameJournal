package agus.gamejournal.app.controller;

import agus.gamejournal.app.dto.GameCoverDTO;
import agus.gamejournal.app.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/v1/games/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class GameController {
    private final GameService gameService;

    @GetMapping(path = "/covers")
    public ResponseEntity<Page<GameCoverDTO>> getGamesCovers(@PageableDefault(size = 10) Pageable pageable){
        return ResponseEntity.ok().body(gameService.findAllGamesCovers(pageable));
    }
}
