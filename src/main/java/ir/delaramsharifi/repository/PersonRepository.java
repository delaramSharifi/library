package ir.delaramsharifi.repository;

import ir.delaramsharifi.domain.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Integer> {

    PersonEntity findByNameContains(String name);
}
