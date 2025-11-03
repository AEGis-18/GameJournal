package agus.gamelist.app.controller;

import agus.gamelist.app.repository.GameRepository;
import agus.gamelist.app.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;

@RestController
@RequestMapping("/api/v1/games/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class GameController {
    private final GameService gameService;

    @GetMapping(path = "/covers")
    public ResponseEntity<?> getGamesCovers() {
        System.out.println("getGamesCovers");
        return ResponseEntity.ok().body(gameService.getAllGamesCovers());
    }
}
