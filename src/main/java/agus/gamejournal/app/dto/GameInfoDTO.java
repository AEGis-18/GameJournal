package agus.gamejournal.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameInfoDTO {
   private String slug;
   private Long id;
   private String title;
   private String summary;
//   private String coverId;
//   @JsonProperty("cover_url")
   private String coverUrl;
   private List<String> genres;
   private List<String> developers;
   private List<String> publishers;
   //Add release Dates with platforms
//   private List<>
}
