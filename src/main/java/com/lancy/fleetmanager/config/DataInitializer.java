package com.lancy.fleetmanager.config;

import com.lancy.fleetmanager.model.Vehicle;
import com.lancy.fleetmanager.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    @Bean
    CommandLineRunner initDatabase(VehicleRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                log.info("Seeding initial fleet data to PostgreSQL...");
                repository.saveAll(List.of(
                    new Vehicle("V1", "Tesla Model 3", "ACTIVE", 88.5),
                    new Vehicle("V2", "Ford F-150 Lightning", "CHARGING", 42.0),
                    new Vehicle("V3", "Rivian R1T", "MAINTENANCE", 15.2)
                ));
                log.info("✅ Database seeded successfully.");
            } else {
                log.info("Database already contains data, skipping seed.");
            }
        };
    }
}
