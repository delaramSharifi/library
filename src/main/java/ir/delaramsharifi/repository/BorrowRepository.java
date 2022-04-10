package ir.delaramsharifi.repository;

import ir.delaramsharifi.domain.BorrowEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowRepository extends CrudRepository<BorrowEntity,Integer> {

    Optional<BorrowEntity> findByBook_IdAndMember_Id(Integer bookId, Integer memberId);

}
