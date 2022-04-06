package ir.delaramsharifi.service;

import ir.delaramsharifi.model.PersonDto;

import java.util.List;

public interface PersonService {

    PersonDto save(PersonDto newPersonDto);

    List<PersonDto> findAll();
}
