package be.odisee.travelbase.dao;

import be.odisee.travelbase.domain.Evaluatiefiche;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EvaluatieficheRepository extends CrudRepository<Evaluatiefiche, Long> {

    /**
     * The default findById would return Optional<Evaluatiefiche>
     * We want a Evaluatiefiche object as return
     * therefore we override this method
     * @param id
     * @return
     */
    public Evaluatiefiche findById(long id);

    public List<Evaluatiefiche> findAllByOrderByNaam();
}
