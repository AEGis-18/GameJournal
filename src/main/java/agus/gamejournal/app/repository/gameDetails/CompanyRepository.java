package agus.gamejournal.app.repository.gameDetails;

import agus.gamejournal.app.model.gameDetails.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
