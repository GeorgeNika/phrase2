package il.george_nika.phrase2.model.dao;

import il.george_nika.phrase2.model.data.adverb.Adverb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdverbRepository extends CrudRepository<Adverb, Integer> {
    List<Adverb> findAll();
    Adverb getAdverbById(Integer id);

    @Query("SELECT adv FROM Adverb adv JOIN adv.mainForm lu WHERE lu.russian LIKE  :filter OR lu.hebrew LIKE  :filter")
    Page<Adverb> getAdverbsWithFilter(Pageable pageable, @Param("filter") String filter);

    @Query("SELECT adv FROM Adverb adv")
    Page<Adverb> getAdverbsWithoutFilter(Pageable pageable);

    @Query(value="SELECT * FROM Adverb ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Adverb getRandomAdverb();

}
