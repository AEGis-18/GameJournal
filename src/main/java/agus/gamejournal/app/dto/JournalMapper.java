package agus.gamejournal.app.dto;

import agus.gamejournal.app.model.Journal;
import agus.gamejournal.app.model.gameDetails.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface JournalMapper {
    JournalMapper INSTANCE = Mappers.getMapper(JournalMapper.class);

    @Mapping(target ="games", source = "games")
//    @Mapping(source="game.genres", target="game.genres")
    JournalDTO journalToJournalDTO(Journal journal);
    Journal journalDTOToJournal(JournalDTO journalDTO);

    default List<String> mapGenresToGenreNames(List<Genre> genres){
        return genres.stream().map(Genre::getName).collect(Collectors.toList());
    }

    default List<Genre> mapGenreNamesToGenres(List<String> genreNames){
        return genreNames.stream().map(genreName->Genre.builder().name(genreName).build()).collect(Collectors.toList());
    }

}
