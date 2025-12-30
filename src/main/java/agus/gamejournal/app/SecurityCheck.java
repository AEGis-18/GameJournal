package agus.gamejournal.app;

import agus.gamejournal.app.repository.JournalRepository;
import agus.gamejournal.app.repository.UserJournalRepository;
import agus.gamejournal.authSecurity.security.services.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityCheck {
    private final UserJournalRepository userJournalRepository;

    public boolean canAccessJournal(Long journalId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof UserDetailsImpl userDetails)) {
            System.out.println("Not logged in");
            return false;
        }

        System.out.println("Logged in~~~");
        return userJournalRepository.existsByUserIdAndJournalId(userDetails.getId(), journalId);
    }
}
