package il.george_nika.phrase2.model.dao;

import il.george_nika.phrase2.model.data.adjective.AdjectiveData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdjectiveDataRepository extends CrudRepository<AdjectiveData, Integer> {

}

