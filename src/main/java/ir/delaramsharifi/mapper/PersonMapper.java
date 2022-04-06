package ir.delaramsharifi.mapper;


import ir.delaramsharifi.domain.PersonEntity;
import ir.delaramsharifi.model.PersonDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PersonMapper {

    @Named("toPersonDto")
    PersonDto toPersonDto(PersonEntity source);

    @Named("toPersonEntity")
    @InheritInverseConfiguration(name = "toPersonDto")
    PersonEntity toPersonEntity(PersonDto source);

    @IterableMapping(qualifiedByName = "toPersonDto")
    List<PersonDto> toPersonDtos(List<PersonEntity> sources);

    @IterableMapping(qualifiedByName = "toPersonEntity")
    List<PersonEntity> toPersonEntities(List<PersonDto> sources);
}
