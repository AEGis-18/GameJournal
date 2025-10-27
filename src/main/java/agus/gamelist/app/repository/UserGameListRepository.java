package agus.gamelist.app.repository;

import agus.gamelist.app.model.UserGameList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGameListRepository extends JpaRepository<UserGameList, Long> {
}
