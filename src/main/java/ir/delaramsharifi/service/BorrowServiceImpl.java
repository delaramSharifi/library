package ir.delaramsharifi.service;

import ir.delaramsharifi.domain.BorrowEntity;
import ir.delaramsharifi.mapper.BookMapper;
import ir.delaramsharifi.mapper.BorrowMapper;
import ir.delaramsharifi.model.BorrowDto;
import ir.delaramsharifi.repository.BorrowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BorrowServiceImpl implements BorrowService {

    private final BorrowRepository borrowRepository;
    private final BorrowMapper borrowMapper;
    private final BookService bookService;
    private final BookMapper bookMapper;

    @Override
    public BorrowDto saveBorrowAndBook(BorrowDto newBorrowDto) {

        BorrowEntity savedBorrow = borrowRepository.save(borrowMapper.toBorrowEntity(newBorrowDto));
        bookService.save(newBorrowDto.getBookDto());

        return Optional.of(savedBorrow)
                .filter(Objects::nonNull)
                .map(borrowMapper::toBorrowDto)
                .orElse(null);

    }

    @Override
    public BorrowDto findByBookIdAndMemberId(BorrowDto updateBorrowDto) {
        return borrowRepository
                .findByBook_IdAndMember_Id(updateBorrowDto.getBookId(),updateBorrowDto.getMemberId())
                .filter(Objects::nonNull)
                .map(borrowMapper::toBorrowDto)
                .orElse(null);
    }
}
