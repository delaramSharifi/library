package ir.delaramsharifi.mapper;

import ir.delaramsharifi.domain.BorrowEntity;
import ir.delaramsharifi.model.BorrowDto;
import ir.delaramsharifi.service.BookService;
import ir.delaramsharifi.service.MemberService;
import ir.delaramsharifi.utils.DateTimeUtils;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        imports = {DateTimeUtils.class},
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


    @Mappings({
            @Mapping(target = "dueDate", expression = "java(DateTimeUtils.getToDayAsPersianString())"),
            @Mapping(target = "bookDto",source = "bookDto",qualifiedByName = "toBookDto_setActivityStatusToFalse")
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BorrowDto toBorrowDto_setTodayForDueDateAndBookActivityStatusFalse(BorrowDto source);


    @Mappings({
            @Mapping(target = "returnDate", expression = "java(DateTimeUtils.getToDayAsPersianString())"),
            @Mapping(target = "bookDto",source = "bookDto",qualifiedByName = "toBookDto_setActivityStatusToTrue")
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BorrowDto toBookDto_setTodayForReturnDateAndBookActivityStatusTrue(BorrowDto source);


    @Mappings({
            @Mapping(target = "issue", expression = "java(String.valueOf(source))")
    })
    void updateBorrowDtoSetIssue(@MappingTarget BorrowDto target, String source);
}
