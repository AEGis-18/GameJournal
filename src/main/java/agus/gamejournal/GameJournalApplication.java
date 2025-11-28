package agus.gamejournal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GameJournalApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameJournalApplication.class, args);
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "GameJournal is UP!";
    }
}
