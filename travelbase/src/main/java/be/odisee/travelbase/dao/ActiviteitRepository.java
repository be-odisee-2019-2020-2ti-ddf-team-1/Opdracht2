package be.odisee.travelbase.dao;

import be.odisee.travelbase.domain.Activiteit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActiviteitRepository extends CrudRepository<Activiteit, Long> {

    /**
     * Look up a activity based on its unique name
     */
    public Activiteit findActiviteitByNaam(String naam);

    /**
     * List all activiteiten, order alphabetically by name
     */
    public List<Activiteit> findAllByOrderByNaam();
}
