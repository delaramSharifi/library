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
    List<BookDto> toBookDtos(List<BookEntity> sources);

    @IterableMapping(qualifiedByName = "toBookEntity")
    List<BookEntity> toBookEntities(List<BookDto> sources);

    @Named("toBookDto_setActivityStatusToFalse")
    @Mappings({
            @Mapping(target = "activityStatus", constant = "false")
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BookDto toBookDto_setActivityStatusToFalse(BookDto source);

    @Named("toBookDto_setActivityStatusToTrue")
    @Mappings({
            @Mapping(target = "activityStatus", constant = "true")
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BookDto toBookDto_setActivityStatusToTrue(BookDto source);

}
