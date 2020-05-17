package be.odisee.travelbase;

import be.odisee.travelbase.dao.ActiviteitRepository;
import be.odisee.travelbase.dao.EvaluatieFicheRepository;
import be.odisee.travelbase.dao.GebruikerRepository;
import be.odisee.travelbase.domain.Activiteit;
import be.odisee.travelbase.domain.EvaluatieFiche;
import be.odisee.travelbase.domain.Gebruiker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Database initializer that populates the database with some
 * initial data.
 * <p>
 * This component is started only when app.db-init property is set to true
 */
@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
public class TravelbaseApplicationInitDB implements CommandLineRunner {

    @Autowired
    ActiviteitRepository activiteitRepository;

    @Autowired
    GebruikerRepository gebruikerRepository;

    @Autowired
    EvaluatieFicheRepository evaluatieFicheRepository;

    @Override
    public void run(String... args) throws Exception {

        List<Gebruiker> gebruikers = Arrays.asList(
                new Gebruiker(0, "John Pakketmaker", "PAKKETMAKER", "pakketuser",
                        "{bcrypt}$2a$10$rXz3VKEjxLd9KTYxCtZQHu74DIq4Ag8NYU/ApfXa7qD6TGdp00WPa"),
                new Gebruiker(0, "An Bel", "VERKENNER", "user",
                        "{bcrypt}$2a$10$rXz3VKEjxLd9KTYxCtZQHu74DIq4Ag8NYU/ApfXa7qD6TGdp00WPa")


        );

        for (Gebruiker gebruiker : gebruikers) {
            // gebruiker must be saved for it to have an id
            gebruikerRepository.save(gebruiker);

        }

        for (Gebruiker gebruiker : gebruikers) {
            List<Activiteit> activiteiten = Arrays.asList(
                    new Activiteit(1, "Atomium", gebruiker),
                    new Activiteit(2, "BusSightSeeing", gebruiker),
                    new Activiteit(3, "MiniEurope", gebruiker),
                    new Activiteit(4, "BelgianFood", gebruiker),
                    new Activiteit(5, "Tour", gebruiker),
                    new Activiteit(6, "ChocolateFactory", gebruiker)
            );

            for (Activiteit activiteit : activiteiten) {
                activiteitRepository.save(activiteit);
            }
        }
    }
}
