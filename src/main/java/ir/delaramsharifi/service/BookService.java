package ir.delaramsharifi.service;

import ir.delaramsharifi.mapper.MappingUtil;
import ir.delaramsharifi.model.BookDto;

import java.util.List;

@MappingUtil.BookService
public interface BookService {

    BookDto save(BookDto newBook);

    @MappingUtil.BookIdToBookEntity
    BookDto findBookDtoById(Integer bookId);

    void deleteBook(Integer bookId);

    List<BookDto> findAll();

    BookDto titleContains(String title);
}
