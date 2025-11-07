package agus.gamejournal.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "journal")
public class Journal {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "journal")
    private List<JournalGame> games;

    @JsonIgnore
    @OneToMany(mappedBy = "journal")
    private List<UserJournal> userJournals;

    private String name;
    private String description;

    @CreationTimestamp
    private Date creationDate;
}
