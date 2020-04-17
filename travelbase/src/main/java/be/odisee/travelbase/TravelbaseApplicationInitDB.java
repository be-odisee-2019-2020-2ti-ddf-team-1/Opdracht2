package be.odisee.travelbase;

import be.odisee.travelbase.dao.ActiviteitRepository;
import be.odisee.travelbase.dao.EvaluatieficheRepository;
import be.odisee.travelbase.domain.Activiteit;
import be.odisee.travelbase.domain.Evaluatiefiche;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Database initializer that populates the database with some
 * initial data.
 *
 * This component is started only when app.db-init property is set to true
 */
@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
public class TravelbaseApplicationInitDB implements CommandLineRunner {

    @Autowired
    ActiviteitRepository activiteitRepository;

    @Autowired
    EvaluatieficheRepository evaluatieficheRepository;


    @Override
    public void run(String... args) throws Exception {

        List<Activiteit> activiteiten = Arrays.asList(
                new Activiteit(1, "BXL_Atomium"),
                new Activiteit(2, "BXL_BusSightSeeing"),
                new Activiteit(3, "BXL_MiniEurope"),
                new Activiteit(4, "BXL_BelgianFood"),
                new Activiteit(5, "BXL_Tour"),
                new Activiteit(6, "BXL_ChocolateFactory")

                );

        for (Activiteit activiteit : activiteiten) {
            activiteitRepository.save(activiteit);
        };

        List<Evaluatiefiche> evaluatiefiches = Arrays.asList(
                new Evaluatiefiche(1, "EF_BXL_Atomium", activiteitRepository.findActiviteitByNaam("BXL_Atomium")),
                new Evaluatiefiche(2, "EF_BXL_BusSightSeeing", activiteitRepository.findActiviteitByNaam("BXL_BusSightSeeing")),
                new Evaluatiefiche(3, "EF_BXL_MiniEurope", activiteitRepository.findActiviteitByNaam("BXL_MiniEurope")),
                new Evaluatiefiche(4, "EF_BXL_BelgianFood", activiteitRepository.findActiviteitByNaam("BXL_BelgianFood")),
                new Evaluatiefiche(5, "EF_BXL_Tour", activiteitRepository.findActiviteitByNaam("BXL_Tour")),
                new Evaluatiefiche(6, "EF_BXL_ChocolateFactory", activiteitRepository.findActiviteitByNaam("BXL_ChocolateFactory"))

                );

        for (Evaluatiefiche evaluatiefiche: evaluatiefiches) {
            evaluatieficheRepository.save(evaluatiefiche);
        }

    }
}
