package ir.delaramsharifi.controller;


import ir.delaramsharifi.model.BookDto;
import ir.delaramsharifi.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController()
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @PostMapping(path = "/new",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> createBook(@RequestBody BookDto newBook) {

        BookDto savedBookDto = bookService.save(newBook);

        return ResponseEntity.ok(savedBookDto);
    }

    @PutMapping(path = "/update",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> updateBook(@RequestBody BookDto newBook) {

        BookDto savedBookDto = bookService.save(newBook);

        return ResponseEntity.ok(savedBookDto);
    }

    @GetMapping(path = "/{bookId}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> findById(@PathVariable Integer bookId) {
        BookDto findBookDto = bookService.findBookDtoById(bookId);
        return ResponseEntity.ok(findBookDto);
    }

    @DeleteMapping(path = "/{bookId}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> deleteById(@PathVariable Integer bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.ok("book Deleted!");
    }

    @GetMapping(path = "/all",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> allBooks() {
        List<BookDto> bookDtos = bookService.findAll();
        return ResponseEntity.ok(bookDtos);
    }
}
