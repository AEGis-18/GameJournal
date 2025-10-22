package agus.gamelist.app.repository.gameDetails;

import agus.gamelist.app.model.gameDetails.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
