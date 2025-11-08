package agus.gamejournal.app.controller;

import agus.gamejournal.app.dto.JournalDTO;
import agus.gamejournal.app.dto.JournalGameDTO;
import agus.gamejournal.app.dto.JournalGameRequest;
import agus.gamejournal.app.dto.JournalMapper;
import agus.gamejournal.app.model.Journal;
import agus.gamejournal.app.model.JournalGameId;
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
    private final GameService gameService;
    private final UserJournalService userJournalService;
    private final JournalGameService journalGameService;

    @GetMapping(path = "/u/{userId}")
    public ResponseEntity<?> getUserJournal(@PathVariable Long userId) {
        UserJournal newUserJournal = userJournalService.createUserJournal(userId);
        if(newUserJournal !=null){
            Journal newJournal = newUserJournal.getJournal();
            JournalDTO newJournalDTO = JournalMapper.INSTANCE.journalToJournalDTO(newJournal);
            return ResponseEntity.ok().body(newJournalDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> addGame(@RequestBody JournalGameRequest journalGameRequest) {
//        System.out.println("JournalController addGame"+ journalGameRequest);
        JournalGameDTO journalGame= journalGameService.addToJournal(journalGameRequest);
        if(journalGame!=null){
            return ResponseEntity.ok().body(journalGame);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/delete/{journalId}/{gameId}")
    public ResponseEntity<?> deleteGame(@PathVariable Long journalId, @PathVariable Long gameId) {
//        System.out.println("JournalId "+ journalId);
//        System.out.println("GameId "+ gameId);
        JournalGameId journalGameId = new JournalGameId(gameId, journalId);
        journalGameService.removeFromJournal(journalGameId);
        return ResponseEntity.ok().body("Game has been deleted");
    }

}
