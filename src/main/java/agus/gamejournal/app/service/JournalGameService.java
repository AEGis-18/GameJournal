package agus.gamejournal.app.service;

import agus.gamejournal.app.repository.JournalGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JournalGameService {
    private JournalGameRepository journalGameRepository;
}
