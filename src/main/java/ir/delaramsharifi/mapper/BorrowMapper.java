package ir.delaramsharifi.mapper;

import ir.delaramsharifi.domain.BorrowEntity;
import ir.delaramsharifi.model.BorrowDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {BookMapper.class,MemberMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface BorrowMapper {

    @Named("toBorrowDto")
    @Mappings({
            @Mapping(target="bookDto", source="book",qualifiedByName = "toBookDto"),
            @Mapping(target="memberDto", source="member",qualifiedByName = "toMemberDto")

    })
    BorrowDto toBorrowDto(BorrowEntity source);

    @Named("toBorrowEntity")
    @Mappings({
            @Mapping(target="book", source="bookDto",qualifiedByName = "toBookEntity"),
            @Mapping(target="member", source="memberDto",qualifiedByName = "toMemberEntity")

    })
    @InheritInverseConfiguration(name = "toBorrowDto")
    BorrowEntity toBorrowEntity(BorrowDto source);

    @IterableMapping(qualifiedByName = "toBorrowDto")
    List<BorrowDto> toBorrowDtos(List<BorrowEntity> sources);

    @IterableMapping(qualifiedByName = "toBorrowEntity")
    List<BorrowEntity> toBorrowEntities(List<BorrowDto> sources);
}
