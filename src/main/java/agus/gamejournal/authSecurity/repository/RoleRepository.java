package agus.gamejournal.authSecurity.repository;

import agus.gamejournal.authSecurity.models.ERole;
import agus.gamejournal.authSecurity.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(ERole name);
}
