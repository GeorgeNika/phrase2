package il.george_nika.phrase2.model.dao;

import il.george_nika.phrase2.model.noun.Noun;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NounRepository extends CrudRepository<Noun, Integer> {
    List<Noun> findAll();
}
