package ir.delaramsharifi.service;

import ir.delaramsharifi.model.BorrowDto;

public interface BorrowService {

    BorrowDto save(BorrowDto newBorrowDto);

    BorrowDto findByBookIdAndMemberId(BorrowDto updateBorrowDto);
}
