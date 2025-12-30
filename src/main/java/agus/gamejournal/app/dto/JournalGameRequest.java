package agus.gamejournal.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JournalGameRequest {
//    @JsonProperty("journal_id")
    private Long journalId;
//    @JsonProperty("game_id")
    private Long gameId;
    private String comment;
    private Integer score;
}
