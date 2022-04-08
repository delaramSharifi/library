package ir.delaramsharifi.mapper;

import ir.delaramsharifi.domain.MemberEntity;
import ir.delaramsharifi.model.MemberDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface MemberMapper {

    @Named("toMemberDto")
    MemberDto toMemberDto(MemberEntity source);

    @Named("toMemberEntity")
    @InheritInverseConfiguration(name = "toMemberDto")
    MemberEntity toMemberEntity(MemberDto source);

    @IterableMapping(qualifiedByName = "toMemberDto")
    List<MemberDto> toMemberDtos(List<MemberEntity> sources);

    @IterableMapping(qualifiedByName = "toMemberEntity")
    List<MemberEntity> toMemberEntities(List<MemberDto> sources);


}
