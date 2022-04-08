package ir.delaramsharifi.repository;

import ir.delaramsharifi.domain.BorrowEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepository extends CrudRepository<BorrowEntity,Integer> {

}
