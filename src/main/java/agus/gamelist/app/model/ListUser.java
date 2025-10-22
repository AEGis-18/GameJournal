package agus.gamelist.app.model;

import agus.gamelist.authSecurity.models.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class ListUser {
    @Id
    @OneToOne
    @JoinColumn(name = "user_id")
    private User listUser;

    @ManyToMany(mappedBy = "listUsers")
    private List<GameList> gameLists;
}
