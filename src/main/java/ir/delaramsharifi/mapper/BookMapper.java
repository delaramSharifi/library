package ir.delaramsharifi.mapper;

import ir.delaramsharifi.domain.BookEntity;
import ir.delaramsharifi.model.BookDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface BookMapper {

    @Named("toBookDto")
    BookDto toBookDto(BookEntity source);

    @Named("toBookEntity")
    @InheritInverseConfiguration(name = "toBookDto")
    BookEntity toBookEntity(BookDto source);

    @IterableMapping(qualifiedByName = "toBookDto")
    List<BookDto> tBookDtos(List<BookEntity> sources);

    @IterableMapping(qualifiedByName = "toBookEntity")
    List<BookEntity> toBookEntities(List<BookDto> sources);
}
