package agus.gamelist.app.model;

import agus.gamelist.authSecurity.models.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class GameList {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    @JoinTable(name = "game_list_list_user",
            joinColumns = @JoinColumn(name = "game_list_id"),
            inverseJoinColumns = @JoinColumn(name = "list_user_id")
    )
    private List<ListUser> listUsers;

    @ManyToMany
    @JoinTable(name = "game_list_game",
            joinColumns = @JoinColumn(name = "game_list_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private List<Game> games;

    private String name;
    private String description;

    @CreationTimestamp
    private Date creationDate;
}
