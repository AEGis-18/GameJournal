package agus.gamejournal.app.service;

import agus.gamejournal.app.model.Journal;
import agus.gamejournal.app.model.UserJournal;
import agus.gamejournal.app.repository.UserJournalRepository;
import agus.gamejournal.authSecurity.models.User;
import agus.gamejournal.authSecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserJournalService {
    final private UserJournalRepository userJournalRepository;
    final private UserRepository userRepository;
    private final JournalService journalService;

    public UserJournal createUserJournal(Long userId) {
       Optional<User> user=userRepository.findById(userId);

      if(user.isPresent()){
          Optional<UserJournal> userJournal=
                  userJournalRepository.findByUserId(userId);

          if(userJournal.isPresent()){
              return userJournal.get();
          }

          UserJournal newUserJournal =new UserJournal();
          Journal journal = journalService.createJournal();
          newUserJournal.setJournal(journal);
          newUserJournal.setUser(user.get());

          return userJournalRepository.save(newUserJournal);
      }
      //TODO check specific exception
        throw new RuntimeException("User not found with ID: " + userId);
    }

    public Optional<Journal> findJournalByUserId(Long userId) {
        return userJournalRepository.findByUserId(userId)
                .map(userJournal -> journalService.findById(userJournal.getJournal().getId()))
                .orElse(Optional.empty());
    }

}
