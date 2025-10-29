package agus.gamelist.app.model.gameDetails;

import agus.gamelist.app.model.Game;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "game_company")
public class GameCompany {
    @Id
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Id
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    private Boolean developer;
    private Boolean publisher;
}
