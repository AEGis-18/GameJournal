package agus.gamejournal.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JournalGameRequest {
    private Long JournalId;
    private Long gameId;
    private String comment;
    private Integer score;
}
