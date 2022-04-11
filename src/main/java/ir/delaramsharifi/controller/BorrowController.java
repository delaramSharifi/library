package ir.delaramsharifi.controller;


import ir.delaramsharifi.exception.controller.NotAcceptableException;
import ir.delaramsharifi.exception.controller.NotFoundException;
import ir.delaramsharifi.mapper.BookMapper;
import ir.delaramsharifi.mapper.BorrowMapper;
import ir.delaramsharifi.model.BookDto;
import ir.delaramsharifi.model.BorrowDto;
import ir.delaramsharifi.model.MemberDto;
import ir.delaramsharifi.service.BookService;
import ir.delaramsharifi.service.BorrowService;
import ir.delaramsharifi.service.MemberService;
import ir.delaramsharifi.utils.DateTimeUtils;
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
    private final MemberService memberService;
    private final BorrowMapper borrowMapper;

    @PostMapping(path = "/newBook",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> newBook(@RequestBody BorrowDto newBorrow) {

        MemberDto memberDto = memberService.findMemberDtoById(newBorrow.getMemberId());

        if (memberDto == null) {
            throw new NotFoundException("Member Not Found");
        } else {
            newBorrow.setMemberDto(memberDto);
        }

        if (DateTimeUtils.isTodayAfterPersianDate(memberDto.getExpiryDate())) {
            throw new NotAcceptableException("Member Date is Expired");
        }

        BookDto bookDto = bookService.findBookDtoById(newBorrow.getBookId());

        if (bookDto == null) {
            throw new NotFoundException("Book Not Found");
        } else {
            newBorrow.setBookDto(bookDto);
        }

        if (!bookDto.getActivityStatus()) {
            throw new NotFoundException("Book Is Not Available");
        }

        newBorrow = borrowMapper.toBorrowDto_setTodayForDueDateAndBookActivityStatusFalse(newBorrow);

        BorrowDto savedBorrowDto = borrowService.saveBorrowAndBook(newBorrow);

        return ResponseEntity.ok(savedBorrowDto);
    }

    @PutMapping(path = "/returnBook",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> returnBook(@RequestBody BorrowDto updateBorrow) {

        BorrowDto foundBorrowDto = borrowService.findByBookIdAndMemberId(updateBorrow);

        if (foundBorrowDto == null) {
            throw new NotFoundException("Data Not Found");
        }

        borrowMapper.updateBorrowDtoSetIssue(foundBorrowDto, updateBorrow.getIssue());
        updateBorrow = borrowMapper.toBookDto_setTodayForReturnDateAndBookActivityStatusTrue(foundBorrowDto);

        BorrowDto savedBorrowDto = borrowService.saveBorrowAndBook(updateBorrow);

        return ResponseEntity.ok(savedBorrowDto);
    }

}
