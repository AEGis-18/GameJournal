package agus.gamejournal.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record JournalIdResponse(
        @JsonProperty("journal_id")
        Long journalId
) {}
