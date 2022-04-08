package ir.delaramsharifi.service;

import ir.delaramsharifi.domain.BookEntity;
import ir.delaramsharifi.mapper.BookMapper;
import ir.delaramsharifi.model.BookDto;
import ir.delaramsharifi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(BookDto newBookDto) {
        BookEntity newBook = bookMapper.toBookEntity(newBookDto);
        BookEntity savedBook = bookRepository.save(newBook);
        return bookMapper.toBookDto(savedBook);
    }

    @Override
    public List<BookDto> findAll() {
        List<BookDto> bookDtos = StreamSupport
                .stream(bookRepository.findAll().spliterator(), false)
                .map(bookMapper::toBookDto)
                .collect(Collectors.toList());
        return bookDtos;
    }

    @Override
    public BookDto findById(Integer id) {
        BookDto findBookDto = bookRepository.findById(id)
                .filter(Objects::nonNull)
                .map(bookMapper::toBookDto)
                .orElse(null);
        return findBookDto;
    }

    @Override
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookDto titleContains(String title) {
        BookEntity bookContains = bookRepository.findByTitleContains(title);
        return bookMapper.toBookDto(bookContains);
    }
}
