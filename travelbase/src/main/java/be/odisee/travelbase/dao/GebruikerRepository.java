package be.odisee.travelbase.dao;

import be.odisee.travelbase.domain.Gebruiker;
import org.springframework.data.repository.CrudRepository;

public interface GebruikerRepository extends CrudRepository<Gebruiker, Long> {

    // needed for Spring Security
    public Gebruiker findByGebruikersnaam(String gebruikersnaam);
}
