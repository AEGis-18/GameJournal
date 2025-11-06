package agus.gamejournal.app.repository.gameDetails;

import agus.gamejournal.app.model.gameDetails.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization,Long> {
}
