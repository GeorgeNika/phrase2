package il.george_nika.phrase2.model.dao;

import il.george_nika.phrase2.model.verb.Verb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
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

    @Query("SELECT v FROM Verb v JOIN v.infinitive lu WHERE lu.russian LIKE  :filter OR lu.hebrew LIKE  :filter")
    Page<Verb> getVerbsWithFilter(Pageable pageable, @Param("filter") String filter);

    @Query("SELECT v FROM Verb v")
    Page<Verb> getVerbsWithoutFilter(Pageable pageable);

}
