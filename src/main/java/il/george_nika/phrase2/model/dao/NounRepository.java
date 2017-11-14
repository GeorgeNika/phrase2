package il.george_nika.phrase2.model.dao;

import il.george_nika.phrase2.model.noun.Noun;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NounRepository extends CrudRepository<Noun, Integer> {
    List<Noun> findAll();
    Noun getNounById(Integer id);

    @Query("SELECT n FROM Noun n JOIN n.mainForm lu WHERE lu.russian LIKE  :filter OR lu.hebrew LIKE  :filter")
    Page<Noun> getNounsWithFilter(Pageable pageable, @Param("filter") String filter);

    @Query("SELECT n FROM Noun n")
    Page<Noun> getNounsWithoutFilter(Pageable pageable);

    @Query(value="SELECT * FROM Noun ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Noun getRandomNoun();

}
