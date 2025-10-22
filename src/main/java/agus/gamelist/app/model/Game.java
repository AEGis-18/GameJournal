package agus.gamelist.app.model;

import agus.gamelist.app.model.gameDetails.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank
    @Size(max=150)
    private String title;
    @NotBlank
    @Size(max=150)
    private String slug;

    private String summary;

    @ManyToMany
    @JoinTable(
            name = "game_genre",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre>  genres;

    @ManyToMany
    @JoinTable(
            name = "game_age_rating",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "age_rating_id")
    )
    private List<AgeRating> ageRatings;
    //Get from Release date
    //private List<Platform> platforms;
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<ReleaseDate> releaseDates;
    @ManyToMany
    @JoinTable(name = "game_company",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id")
    )
    private List<Company> companies;

    @ManyToMany(mappedBy = "games")
    private List<GameList> gameLists;

    //TODO check COVER
    //private String cover;
    //private List<Screenshot> screenshots;
}
