package be.odisee.travelbase.dao;

import be.odisee.travelbase.domain.Activiteit;
import be.odisee.travelbase.domain.Gebruiker;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActiviteitRepository extends CrudRepository<Activiteit, Long> {

    /**
     * Look up a activity based on its unique name
     */
    public Activiteit findActiviteitByGebruikerAndNaam(Gebruiker gebruiker, String naam);

    /**
     * List all activiteiten, order alphabetically by name
     */
    public List<Activiteit> findAllByGebruikerOrderByNaam(Gebruiker gebruiker);

    public Activiteit findById(long id);
}

