package agus.gamejournal.app.service;

import agus.gamejournal.app.dto.JournalGameDTO;
import agus.gamejournal.app.dto.JournalGameMapper;
import agus.gamejournal.app.dto.JournalGameRequest;
import agus.gamejournal.app.model.Game;
import agus.gamejournal.app.model.Journal;
import agus.gamejournal.app.model.JournalGame;
import agus.gamejournal.app.model.JournalGameId;
import agus.gamejournal.app.repository.JournalGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import agus.gamejournal.app.dto.JournalGameMapper;

@Service
@RequiredArgsConstructor
public class JournalGameService {

    private final  GameService gameService;
    private final JournalService journalService;
    private final JournalGameRepository journalGameRepository;
    private final JournalGameMapper journalGameMapper;


    public JournalGameDTO addToJournal(JournalGameRequest JournalGame) {
        Game game=
                gameService.findGameById(JournalGame.getGameId()).orElseThrow(()->new RuntimeException("Game not found"));
        Journal journal =
                journalService.findById(JournalGame.getJournalId()).orElseThrow(()->new RuntimeException("Journal not found"));

        JournalGameId journalGameId =new JournalGameId(game.getId(), journal.getId());
        JournalGame newJournalGame =new JournalGame();

        newJournalGame.setId(journalGameId);
        newJournalGame.setJournal(journal);
        newJournalGame.setGame(game);
        newJournalGame.setComment(JournalGame.getComment());
        newJournalGame.setScore(JournalGame.getScore());

        JournalGame journalGame=journalGameRepository.save(newJournalGame);
        return journalGameMapper.journalGameToJournalGameDTO(journalGame);
    }

    public void removeFromJournal(JournalGameId journalGameId) {
        JournalGame journalGame=
                journalGameRepository.findById(journalGameId).orElseThrow(()->new RuntimeException("JournalGame not found"));
        journalGameRepository.delete(journalGame);
    }
}
