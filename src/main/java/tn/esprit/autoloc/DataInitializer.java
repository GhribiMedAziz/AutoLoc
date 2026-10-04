package tn.esprit.autoloc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DataInitializer {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    @Bean
    public CommandLineRunner initVehicules(VehiculeRepository vehiculeRepository) {
        return args -> {
            if (vehiculeRepository.count() == 0) {
                log.info("Insertion des vehicules de demonstration...");
                Vehicule v1 = new Vehicule(
                        null,
                        "200-TN-1234",
                        "Renault",
                        "Clio 5",
                        CategorieVehicule.CITADINE,
                        new BigDecimal("90.00"),
                        StatutVehicule.DISPONIBLE
                );
                Vehicule v2 = new Vehicule(
                        null,
                        "210-TN-5678",
                        "Volkswagen",
                        "Passat",
                        CategorieVehicule.BERLINE,
                        new BigDecimal("180.00"),
                        StatutVehicule.DISPONIBLE
                );
                Vehicule v3 = new Vehicule(
                        null,
                        "220-TN-9012",
                        "Peugeot",
                        "3008",
                        CategorieVehicule.SUV,
                        new BigDecimal("220.00"),
                        StatutVehicule.MAINTENANCE
                );
                vehiculeRepository.saveAll(List.of(v1, v2, v3));
                log.info("3 vehicules de demonstration inseres avec succes.");
            }
        };
    }
}
