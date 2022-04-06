package ir.delaramsharifi.controller;


import ir.delaramsharifi.model.PersonDto;
import ir.delaramsharifi.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController()
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @PostMapping(path = "/new",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> newEmployee(@RequestBody PersonDto newPerson) {

        PersonDto savedPersonDto = personService.save(newPerson);

        return ResponseEntity.ok(savedPersonDto);
    }

    @GetMapping(path = "/all",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> newEmployee2() {
        List<PersonDto> personDtos = personService.findAll();
        return ResponseEntity.ok(personDtos);
    }

}
