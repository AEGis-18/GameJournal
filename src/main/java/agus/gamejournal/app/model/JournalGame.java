package agus.gamejournal.app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @MapsId("journal")
    @JoinColumn(name = "journal_id")
    private Journal journal;

    @Min(1)
    @Max(10)
    private Integer score;
    @Size(max = 500)
    private String comment;
}
