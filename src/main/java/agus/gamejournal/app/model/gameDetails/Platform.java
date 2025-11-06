package agus.gamejournal.app.model.gameDetails;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 150)
    private String name;
    //TODO Check logos
//    private String logo;
   // @ManyToMany(mappedBy = "platforms")
    //private List<Game>  games;
}
