package il.george_nika.phrase2.model.dao;

import il.george_nika.phrase2.model.verb.VerbData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerbDataRepository extends CrudRepository<VerbData, Integer> {

}

