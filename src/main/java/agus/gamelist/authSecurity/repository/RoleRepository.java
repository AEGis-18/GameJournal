package agus.gamelist.authSecurity.repository;

import agus.gamelist.authSecurity.models.ERole;
import agus.gamelist.authSecurity.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(ERole name);
}
