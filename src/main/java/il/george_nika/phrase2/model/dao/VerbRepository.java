package il.george_nika.phrase2.model.dao;

import il.george_nika.phrase2.model.verb.Verb;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by George on 30.06.2017.
 */
@Repository
public interface VerbRepository extends CrudRepository<Verb, Integer> {
    List<Verb> findAll();

    Verb getVerbById(Integer id);

    @Query("SELECT id FROM Verb")
    List<Integer> getAllIds();

}
