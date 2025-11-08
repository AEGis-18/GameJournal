package agus.gamejournal.app.controller;

import agus.gamejournal.app.dto.JournalDTO;
import agus.gamejournal.app.dto.JournalGameDTO;
import agus.gamejournal.app.dto.JournalGameRequest;
import agus.gamejournal.app.dto.JournalMapper;
import agus.gamejournal.app.model.Journal;
import agus.gamejournal.app.model.UserJournal;
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
        System.out.println("JournalController addGame"+ journalGameRequest);
        JournalGameDTO journalGame=
                journalService.addGameToJournal(journalGameRequest);
        if(journalGame!=null){
            return ResponseEntity.ok().body(journalGame);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<?> deleteGame(@RequestBody Long gameId) {

        return ResponseEntity.notFound().build();
    }

}
