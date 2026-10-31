package tn.esprit.autoloc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AutolocApiApplicationTests {

    @Test
    void contextLoads() {
        // Verification du chargement correct du contexte applicatif Spring Boot
        assertTrue(true, "Le contexte applicatif demarre avec succes.");
    }
}
