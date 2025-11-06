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
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 150)
    private String name;

    @OneToMany(mappedBy = "organization")
    private List<AgeRating> ageRatings;

    @OneToMany(mappedBy = "organization")
    private List<RatingCategory> ratingCategories;
}
