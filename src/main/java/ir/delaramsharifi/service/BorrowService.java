package ir.delaramsharifi.service;

import ir.delaramsharifi.model.BorrowDto;

public interface BorrowService {

    BorrowDto saveBorrowAndBook(BorrowDto newBorrowDto);

    BorrowDto findByBookIdAndMemberId(BorrowDto updateBorrowDto);
}
