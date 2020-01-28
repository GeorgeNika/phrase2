package il.george_nika.phrase2.model.dao;

import il.george_nika.phrase2.model.data.noun.NounData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NounDataRepository extends CrudRepository<NounData, Integer> {

}

