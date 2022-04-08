package ir.delaramsharifi.service;

import ir.delaramsharifi.domain.PersonEntity;
import ir.delaramsharifi.mapper.PersonMapper;
import ir.delaramsharifi.model.PersonDto;
import ir.delaramsharifi.repository.PersonRepository;
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
@Slf4j
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    public PersonDto save(PersonDto newPersonDto) {
        PersonEntity newPerson = personMapper.toPersonEntity(newPersonDto);
        PersonEntity savedPerson = personRepository.save(newPerson);
        return personMapper.toPersonDto(savedPerson);
    }

    @Override
    public List<PersonDto> findAll() {
        List<PersonDto> personDtos = StreamSupport
                .stream(personRepository.findAll().spliterator(), false)
                .map(personMapper::toPersonDto)
                .collect(Collectors.toList());
        return personDtos;
    }

    @Override
    public PersonDto findById(Integer id) {
        PersonDto findPersonDto = personRepository.findById(id)
                .filter(Objects::nonNull)
                .map(personMapper::toPersonDto)
                .orElse(null);
        return findPersonDto;
    }

    @Override
    public void deletePerson(Integer id) {
        personRepository.deleteById(id);
    }

    @Override
    public PersonDto nameContains(String name) {
        PersonEntity personContains = personRepository.findByNameContains(name);
        return personMapper.toPersonDto(personContains);
    }
}
