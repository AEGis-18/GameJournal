package agus.gamejournal.app.repository;

import agus.gamejournal.app.model.UserJournal;
import agus.gamejournal.authSecurity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJournalRepository extends JpaRepository<UserJournal, Long> {
    Optional<UserJournal> findByUser(User user);
    Optional<UserJournal> findByUserId(Long userId);
}
