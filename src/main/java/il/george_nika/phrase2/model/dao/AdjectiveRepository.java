package il.george_nika.phrase2.model.dao;

import il.george_nika.phrase2.model.adjective.Adjective;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdjectiveRepository extends CrudRepository<Adjective, Integer> {
    List<Adjective> findAll();
    Adjective getAdjectiveById(Integer id);

    @Query("SELECT a FROM Adjective a JOIN a.mainForm lu WHERE lu.russian LIKE  :filter OR lu.hebrew LIKE  :filter")
    Page<Adjective> getAdjectivesWithFilter(Pageable pageable, @Param("filter") String filter);

    @Query("SELECT a FROM Adjective a")
    Page<Adjective> getAdjectivesWithoutFilter(Pageable pageable);

    @Query(value="SELECT * FROM Adjective ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Adjective getRandomAdjective();

}
