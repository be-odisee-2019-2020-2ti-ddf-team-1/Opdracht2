package be.odisee.travelbase.dao;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import be.odisee.travelbase.domain.Entry;
import java.util.List;

public interface EntryRepository extends CrudRepository<Entry, Long> {

    /**
     * The default findById would return Optional<Entry>
     * We want a Entry object as return
     * therefore we override this method
     * @param id
     * @return
     */
    public Entry findById(long id);

    /**
     * @return The entry with the largest id = the most recently added entry
     */
    public Entry findFirstByOrderByIdDesc();


}
