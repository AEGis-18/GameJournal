package agus.gamelist.app.service;

import agus.gamelist.app.model.GameList;
import agus.gamelist.app.model.UserGameList;
import agus.gamelist.app.repository.GameListRepository;
import agus.gamelist.app.repository.UserGameListRepository;
import agus.gamelist.authSecurity.models.User;
import agus.gamelist.authSecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserGameListService {
    final private UserGameListRepository userGameListRepository;
    final private UserRepository userRepository;
    private final GameListService gameListService;

    public UserGameList createUserGameList(Long userId) {
       Optional<User> user=userRepository.findById(userId);

      if(user.isPresent()){
          Optional<UserGameList> userGameList=
                  userGameListRepository.findByUserId(userId);

          if(userGameList.isPresent()){
              return userGameList.get();
          }

          UserGameList newUserGameList=new UserGameList();
          GameList gameList=gameListService.createGameList();
          newUserGameList.setGameList(gameList);
          newUserGameList.setUser(user.get());

          return userGameListRepository.save(newUserGameList);
      }
      //TODO check specific exception
        throw new RuntimeException("User not found with ID: " + userId);
    }

    public Optional<GameList> findGameListByUserId(Long userId) {
        return userGameListRepository.findByUserId(userId)
                .map(userGameList -> gameListService.findById(userGameList.getGameList().getId()))
                .orElse(Optional.empty());
    }

}
