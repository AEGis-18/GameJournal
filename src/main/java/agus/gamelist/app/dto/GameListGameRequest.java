package agus.gamelist.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameListGameRequest {
    private Long gameListId;
    private Long gameId;
    private String comment;
    private Integer score;
}
