package il.george_nika.phrase2.model.dao;

import il.george_nika.phrase2.model.verb.ActionVerb;
import il.george_nika.phrase2.model.verb.Verb;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by George on 02.07.2017.
 */
@Repository
public interface ActionVerbRepository extends CrudRepository<ActionVerb, Integer> {

    List<ActionVerb> findAll();

    @Query("SELECT verb.id FROM ActionVerb")
    List<Integer> getAllActionVerbIds();

    ActionVerb getByVerb(Verb verb);

    @Query(value="SELECT * FROM active_verb ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    ActionVerb getRandomActionVerb();

}
