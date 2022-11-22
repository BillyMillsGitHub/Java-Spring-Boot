package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
//using API with in memory database (Postman)

@RequestMapping("/api/v1/person")//this references the address for the Postman app
@RestController
public class PersonController {

    private final PersonService personService;
    @Autowired //Spring Boot will inject the service into this constructor.

    public PersonController(PersonService personService) {
        this.personService = personService;
    }
@PostMapping
    public void addPerson(@Valid @NonNull @RequestBody Person person) {
        personService.addPerson(person); //need to serve this method as POST REQUEST
        // (add resource to server)
    //@RequestBody allows you to extract the information for person from Postman app and
    // place it inside the person class

        //use if you do not want to produce integer or use to produce status code
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return personService.getAllPeople();//this method serves as a GET request
    }

    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id) { //created @PathVariable annotation allows us to reference ID
        return personService.getPersonById(id)
                .orElse(null);//this method will return null when searching
        // for a person with no ID.
        // Can insert custom message/exception/code i.e. 404 OR "user not found".
    }

    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id) {
        personService.deletePerson(id);
    }


    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Person personToUpdate) {
        personService.updatePerson(id, personToUpdate);
    }
//NOTE:
//Universally Unique Identifier (UUID) is generally used for identifying information
// that needs to be unique with a system or network thereof.

}
