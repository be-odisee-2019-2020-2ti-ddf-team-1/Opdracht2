package be.odisee.travelbase.dao;

import be.odisee.travelbase.domain.Activiteit;
import be.odisee.travelbase.domain.EvaluatieFiche;
import be.odisee.travelbase.domain.Gebruiker;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EvaluatieFicheRepository extends CrudRepository<EvaluatieFiche, Long> {

    /**
     * The default findById would return Optional<EvaluatieFiche>
     * We want a EvaluatieFiche object as return
     * therefore we override this method
     * @param id
     * @return
     */
    public EvaluatieFiche findById(long id);

    /**
     * @return The EvaluatieFiche with the largest id = the most recently added EvaluatieFiche
     */
    public EvaluatieFiche findFirstByGebruikerOrderByIdDesc(Gebruiker gebruiker);

    public List<EvaluatieFiche> findAllByGebruiker(Gebruiker gebruiker);


}
