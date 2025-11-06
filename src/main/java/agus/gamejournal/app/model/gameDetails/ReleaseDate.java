package agus.gamejournal.app.model.gameDetails;

import agus.gamejournal.app.model.Game;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class ReleaseDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform_id",referencedColumnName = "id",nullable = false)
    private Platform platform;

    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;
}
