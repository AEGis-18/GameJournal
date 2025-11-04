package agus.gamelist.app.dto;

import agus.gamelist.app.model.Game;
import agus.gamelist.app.model.gameDetails.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface GameCoverMapper {
    GameCoverMapper INSTANCE = Mappers.getMapper(GameCoverMapper.class);

    @Mapping(target = "genres", source = "genres")
    @Mapping(target = "releaseYear", source = "releaseDate")
    GameCoverDTO gameToGameCoverDTO(Game game);
    Game gameCoverDTOToGame(GameCoverDTO game);

    default List<String> mapGenresToGenreNames(List<Genre> genres){
        return genres.stream().map(Genre::getName).collect(Collectors.toList());
    }

    default List<Genre> mapGenreNamestoGenres(List<String> genreNames){
        return genreNames.stream().map(genreName->Genre.builder().name(genreName).build()).collect(Collectors.toList());
    }
}

