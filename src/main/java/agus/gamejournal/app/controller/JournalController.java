package agus.gamejournal.app.controller;

import agus.gamejournal.app.model.UserJournal;
import agus.gamejournal.app.service.JournalGameService;
import agus.gamejournal.app.service.JournalService;
import agus.gamejournal.app.service.GameService;
import agus.gamejournal.app.service.UserJournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/game-journal/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class JournalController {
    private final JournalService journalService;
    private final JournalGameService journalGameService;
    private final GameService gameService;
    private final UserJournalService userJournalService;

    @GetMapping(path = "/u/{userId}")
    public ResponseEntity<?> getUserGameJournal(@PathVariable Long userId) {
        UserJournal newUserJournal = userJournalService.createUserJournal(userId);
        if(newUserJournal !=null){
            return ResponseEntity.ok().body(newUserJournal.getJournal());
        }
        return ResponseEntity.notFound().build();
    }

}
