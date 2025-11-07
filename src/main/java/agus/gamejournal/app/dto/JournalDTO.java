package agus.gamejournal.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JournalDTO {
    private Long id;
    private List<JournalGameDTO> games;
    private String name;
    private String description;
    private Date creationDate;
}
