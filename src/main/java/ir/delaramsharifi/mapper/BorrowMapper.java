package ir.delaramsharifi.mapper;

import ir.delaramsharifi.domain.BorrowEntity;
import ir.delaramsharifi.model.BorrowDto;
import ir.delaramsharifi.service.BookService;
import ir.delaramsharifi.service.MemberService;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {BookMapper.class, MemberMapper.class, BookService.class, MemberService.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface BorrowMapper {

    @Named("toBorrowDto")
    @Mappings({
            @Mapping(target = "bookDto", source = "book", qualifiedByName = "toBookDto"),
            @Mapping(target = "memberDto", source = "member", qualifiedByName = "toMemberDto")

    })
    BorrowDto toBorrowDto(BorrowEntity source);

    @Named("toBorrowEntity")
    @Mappings({
            @Mapping(target = "book", source = "bookDto", qualifiedByName = "toBookEntity"),
            @Mapping(target = "member", source = "memberDto", qualifiedByName = "toMemberEntity")

    })
    @InheritInverseConfiguration(name = "toBorrowDto")
    BorrowEntity toBorrowEntity(BorrowDto source);

    @IterableMapping(qualifiedByName = "toBorrowDto")
    List<BorrowDto> toBorrowDtos(List<BorrowEntity> sources);

    @IterableMapping(qualifiedByName = "toBorrowEntity")
    List<BorrowEntity> toBorrowEntities(List<BorrowDto> sources);

    @Mappings({
            @Mapping(target = "bookDto", source = "bookId", qualifiedBy = {MappingUtil.BookService.class,
                    MappingUtil.BookIdToBookEntity.class}),
            @Mapping(target = "memberDto", source = "memberId", qualifiedBy = {MappingUtil.MemberService.class,
                    MappingUtil.MemberIdToMemberEntity.class})
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBorrowDto_BookDtoByBookIdAndMemberDtoByMemberId(@MappingTarget BorrowDto target, BorrowDto source);
}
