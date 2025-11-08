package agus.gamejournal.app.service;

import agus.gamejournal.app.dto.*;
import agus.gamejournal.app.model.Game;
import agus.gamejournal.app.model.Journal;
import agus.gamejournal.app.model.JournalGame;
import agus.gamejournal.app.model.JournalGameId;
import agus.gamejournal.app.repository.JournalGameRepository;
import agus.gamejournal.app.repository.JournalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JournalService {
    private final JournalRepository journalRepository;
    private final GameService gameService;
    private final JournalGameRepository journalGameRepository;
    private final GameCoverMapper gameCoverMapper;
    private final JournalGameMapper journalGameMapper;

    public Journal createJournal() {
        Journal journal = new Journal();
        journal.setName("default");
        journal.setDescription("default");
        journal.setCreationDate(new Date());

        return journalRepository.save(journal);
    }

    public Optional<Journal> findById(Long JournalId) {
        return journalRepository.findById(JournalId);
    }
}
