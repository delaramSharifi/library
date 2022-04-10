package ir.delaramsharifi.controller;


import ir.delaramsharifi.mapper.BookMapper;
import ir.delaramsharifi.model.BookDto;
import ir.delaramsharifi.model.BorrowDto;
import ir.delaramsharifi.service.BookService;
import ir.delaramsharifi.service.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController()
@RequestMapping("/borrow")
public class BorrowController {

    private final BorrowService borrowService;
    private final BookService bookService;
    private final BookMapper bookMapper;

    @PostMapping(path = "/newBook",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> newBook(@RequestBody BorrowDto newBorrow) {

//        todo: check membership date is valid
//        todo: check book is activity status is true by search bookId

        BorrowDto savedBorrowDto = borrowService.save(newBorrow);

        return ResponseEntity.ok(savedBorrowDto);
    }

    @PutMapping(path = "/returnBook",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> returnBook(@RequestBody BorrowDto updateBorrow) {

       BorrowDto foundBorrowDto =  borrowService.findByBookIdAndMemberId(updateBorrow);

//       todo: call book service and in book service update book availability to true
        BookDto updatedBook = bookMapper.toBookDto_setActivityStatusToFalse(foundBorrowDto.getBookDto());
//        bookService.save(updatedBook);
        return ResponseEntity.ok(foundBorrowDto);
    }

}
