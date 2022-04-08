package ir.delaramsharifi.service;

import ir.delaramsharifi.model.BookDto;

import java.util.List;

public interface BookService {

    BookDto save(BookDto newBook);

    BookDto findById(Integer bookId);

    void deleteBook(Integer bookId);

    List<BookDto> findAll();

    BookDto titleContains(String title);
}
