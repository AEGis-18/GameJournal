package agus.gamelist.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameCoverDTO {
    private String slug;
    private String title;
    private String coverUrl;
}
