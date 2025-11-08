package agus.gamejournal.app.dto;

import agus.gamejournal.app.model.Game;
import agus.gamejournal.app.model.gameDetails.Company;
import agus.gamejournal.app.model.gameDetails.GameCompany;
import agus.gamejournal.app.model.gameDetails.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface GameInfoMapper {

    GameInfoMapper INSTANCE = Mappers.getMapper(GameInfoMapper.class);

    @Mapping(target = "genres", source = "genres")
    @Mapping(target = "developers", source = "gameCompanies", qualifiedByName = "mapGameCompaniesToDevelopers")
    @Mapping(target = "publishers", source = "gameCompanies", qualifiedByName = "mapGameCompaniesToPublishers")
    GameInfoDTO gameToGameInfoDTO(Game game);
    Game gameInfoDTOToGame(GameInfoDTO game);

    default List<String> mapGenresToGenreNames(List<Genre> genres){
        return genres.stream().map(Genre::getName).collect(Collectors.toList());
    }

    default List<Genre> mapGenreNamesToGenres(List<String> genreNames){
        return genreNames.stream().map(genreName->Genre.builder().name(genreName).build()).collect(Collectors.toList());
    }

    @Named("mapGameCompaniesToDevelopers")
    default List<String> mapGameCompaniesToDevelopers(List<GameCompany> companies){
        return companies.stream()
                .filter(GameCompany::getDeveloper)
                .map(company->company.getCompany().getName())
                .collect(Collectors.toList());
    }

    @Named("mapGameCompaniesToPublishers")
    default List<String> mapGameCompaniesToPublishers(List<GameCompany> companies) {
        return companies.stream()
                .filter(GameCompany::getPublisher)
                .map(company -> company.getCompany().getName())
                .collect(Collectors.toList());
    }
//
//    default List<Genre> mapDevelopersToGameCompanies(List<String> developersNames){
//        return developersNames.stream().map(genreName->Genre.builder().name(genreName).build()).collect(Collectors.toList());
//    }

}
