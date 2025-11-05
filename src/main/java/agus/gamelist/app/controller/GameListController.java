package agus.gamelist.app.controller;

import agus.gamelist.app.model.UserGameList;
import agus.gamelist.app.service.GameListGameService;
import agus.gamelist.app.service.GameListService;
import agus.gamelist.app.service.GameService;
import agus.gamelist.app.service.UserGameListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/game-list/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class GameListController {
    private final GameListService gameListService;
    private final GameListGameService gameListGameService;
    private final GameService gameService;
    private final UserGameListService userGameListService;

    @GetMapping(path = "/u/{userId}")
    public ResponseEntity<?> getUserGameList(@PathVariable Long userId) {
        UserGameList newUserGameList=userGameListService.createUserGameList(userId);
        if(newUserGameList!=null){
            return ResponseEntity.ok().body(newUserGameList.getGameList());
        }
        return ResponseEntity.notFound().build();
    }

}
