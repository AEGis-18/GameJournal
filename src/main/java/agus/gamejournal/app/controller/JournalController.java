package agus.gamejournal.app.controller;

import agus.gamejournal.app.dto.*;
import agus.gamejournal.app.model.Journal;
import agus.gamejournal.app.model.JournalGameId;
import agus.gamejournal.app.model.UserJournal;
import agus.gamejournal.app.service.JournalGameService;
import agus.gamejournal.app.service.JournalService;
import agus.gamejournal.app.service.GameService;
import agus.gamejournal.app.service.UserJournalService;
import agus.gamejournal.authSecurity.security.services.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/game-journal/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class JournalController {
    private final JournalService journalService;
    private final GameService gameService;
    private final UserJournalService userJournalService;
    private final JournalGameService journalGameService;

    @GetMapping(path = "/")
    public ResponseEntity<?> getUserJournal(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Long currentUserId = userDetails.getId();

        UserJournal newUserJournal = userJournalService.createUserJournal(currentUserId);
        if(newUserJournal !=null){
            Journal newJournal = newUserJournal.getJournal();
            JournalDTO newJournalDTO = JournalMapper.INSTANCE.journalToJournalDTO(newJournal);
            return ResponseEntity.ok().body(newJournalDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/u/")
    public ResponseEntity<?> getUserJournalId() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Long currentUserId = userDetails.getId();
        return userJournalService.findJournalIdByUserId(currentUserId)
                .map(id -> ResponseEntity.ok(new JournalIdResponse(id)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/add")
    @PreAuthorize("@securityCheck.canAccessJournal(#journalGameRequest.journalId)")
    public ResponseEntity<?> addGame(@RequestBody JournalGameRequest journalGameRequest) {
//        System.out.println("JournalController addGame"+ journalGameRequest);
        JournalGameDTO journalGame= journalGameService.addToJournal(journalGameRequest);
        if(journalGame!=null){
            return ResponseEntity.ok().body(journalGame);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/delete/{journalId}/{gameId}")
//    @PreAuthorize("@securityCheck.canAccessJournal(#journalGameRequest.journalId)")
    public ResponseEntity<?> deleteGame(@PathVariable Long journalId, @PathVariable Long gameId) {
//        System.out.println("JournalId "+ journalId);
//        System.out.println("GameId "+ gameId);
        JournalGameId journalGameId = new JournalGameId(gameId, journalId);
        journalGameService.removeFromJournal(journalGameId);
        return ResponseEntity.ok().body("Game has been deleted");
    }

}
