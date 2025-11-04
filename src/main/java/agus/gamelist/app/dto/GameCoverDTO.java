package agus.gamelist.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameCoverDTO {
    private String slug;
    private String title;
    private String coverUrl;
    private List<String> genres;
//    private Integer rating;
//    private Integer releaseYear;

}
