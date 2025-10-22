package agus.gamelist.app.repository;

import agus.gamelist.app.model.ListUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListUserRepository extends JpaRepository<ListUser, Long> {
}
