package agus.gamelist.app.repository;

import agus.gamelist.app.model.UserGameList;
import agus.gamelist.authSecurity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserGameListRepository extends JpaRepository<UserGameList, Long> {
    Optional<UserGameList> findByUser(User user);
    Optional<UserGameList> findByUserId(Long userId);
}
