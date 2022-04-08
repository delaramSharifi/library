package ir.delaramsharifi.repository;

import ir.delaramsharifi.domain.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<BookEntity,Integer> {

    BookEntity findByTitleContains(String title);
}
