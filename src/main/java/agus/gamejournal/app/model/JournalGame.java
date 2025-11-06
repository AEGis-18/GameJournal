package agus.gamejournal.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "journal_game")
public class JournalGame {
    @EmbeddedId
    private JournalGameId id;

    @ManyToOne
    @MapsId("game")
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @MapsId("Journal")
    @JoinColumn(name = "journal_id")
    private Journal journal;

    @Min(1)
    @Max(10)
    private Integer score;
    @Size(max = 500)
    private String comment;
}
