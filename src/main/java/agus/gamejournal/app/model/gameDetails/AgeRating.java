package agus.gamejournal.app.model.gameDetails;

import agus.gamejournal.app.model.Game;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class AgeRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
    @ManyToOne
    @JoinColumn(name = "rating_category_id")
    private RatingCategory ratingCategory;

    @ManyToMany(mappedBy = "ageRatings")
    private List<Game> games;
}
