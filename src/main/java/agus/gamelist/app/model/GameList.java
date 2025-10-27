package agus.gamelist.app.model;

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
public class GameList {
    @Id
    @GeneratedValue
    private Long id;


    @ManyToMany
    @JoinTable(name = "game_list_game",
            joinColumns = @JoinColumn(name = "game_list_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private List<Game> games;

    @OneToMany(mappedBy = "gameList")
    private List<UserGameList> userGameLists;

    private String name;
    private String description;

    @CreationTimestamp
    private Date creationDate;
}
