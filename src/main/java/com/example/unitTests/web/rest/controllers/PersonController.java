package com.example.unitTests.web.rest.controllers;

import com.example.unitTests.service.CacheService;
import com.example.unitTests.service.PersonService;
import com.example.unitTests.service.ValidatorService;
import com.example.unitTests.service.model.Person;
import com.example.unitTests.web.rest.models.PersonView;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping("/person")
public class PersonController {
    private PersonService personService;

    private CacheService cacheService;

    private ValidatorService validatorService;

    @Inject
    public PersonController(final PersonService personService, final CacheService cacheService, final ValidatorService validatorService) {
        this.personService = personService;
        this.cacheService = cacheService;
        this.validatorService = validatorService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON},
            produces = {MediaType.APPLICATION_JSON}
    )
    @ResponseStatus(HttpStatus.CREATED)
    public PersonView createPerson(@RequestBody @Valid PersonView personView){
        Person newlyCreatedPerson = personService.createPerson(personView.toDomain());
        return new PersonView(newlyCreatedPerson);
    }

}
