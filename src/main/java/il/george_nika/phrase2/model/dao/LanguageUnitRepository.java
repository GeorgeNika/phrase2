package il.george_nika.phrase2.model.dao;

import il.george_nika.phrase2.model.data.LanguageUnit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageUnitRepository extends CrudRepository<LanguageUnit, Integer> {
}
