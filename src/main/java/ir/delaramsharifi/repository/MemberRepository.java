package ir.delaramsharifi.repository;

import ir.delaramsharifi.domain.MemberEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<MemberEntity,Integer> {

    MemberEntity findByLastNameContains(String lastName);
}
