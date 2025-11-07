package agus.gamejournal.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JournalGameDTO {
    @JsonProperty("journal_id")
    private Long journalId;
//    @JsonProperty("game_id")
    private GameCoverDTO game;
    private String comment;
    private Integer score;
}