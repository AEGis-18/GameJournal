package agus.gamelist.app.dto;

import agus.gamelist.app.model.Game;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GameCoverMapper {
    GameCoverMapper INSTANCE = Mappers.getMapper(GameCoverMapper.class);

    GameCoverDTO gameToGameCoverDTO(Game game);
    Game gameCoverDTOToGame(GameCoverDTO game);
}
