package agus.gamejournal.app.model.gameDetails;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 150)
    private String name;
    @NotBlank
    @Size(max = 150)
    private String slug;

    @OneToMany(mappedBy = "company")
    private List<GameCompany> gameCompanies;
}
