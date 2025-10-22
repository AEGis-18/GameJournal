package agus.gamelist.app.model.gameDetails;

import agus.gamelist.app.model.Game;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 150)
    private String name;
    @NotBlank
    @Size(max = 150)
    private String slug;

    @ManyToMany(mappedBy = "genres")
    private List<Game> games;
}
