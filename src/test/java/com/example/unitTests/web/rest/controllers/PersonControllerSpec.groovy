package com.example.unitTests.web.rest.controllers

import com.example.unitTests.service.CacheService
import com.example.unitTests.service.PersonService
import com.example.unitTests.service.ValidatorService
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc;
import spock.lang.Specification
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class PersonControllerSpec extends Specification {
    def PersonController personController

    def PersonService personService

    def CacheService cacheService

    def ValidatorService validatorService

    def MockMvc mockMvc

    def setup() {
        // Lowest level of dependent class.
        // Can stub method responses
        personService = Stub(PersonService)

        // Middle level of dependent class
        // Can stub method responses
        // Can count number of interactions
        cacheService = Mock(CacheService)

        //Highest level of dependent class
        // Can stub method responses
        // Can count number of interactions
        // Uses Real Class and it's logic for calls
        validatorService = Spy(ValidatorService)

        personController = new PersonController(personService, cacheService, validatorService)

        // If injected and not in the Constructor
        // personController = new PersonController(personService: personService, cacheService: cacheService, validatorService: validatorService)

        mockMvc = standaloneSetup(personController).build()
    }

    def "When I try to create a valid new person in the system I expect a 201 response with the newly created object"() {
        given: "A json request to create a person, and a mocked person to return"
        def json = "{\n" +
                "  \"firstName\":\""+ firstName + "\",\n" +
                "  \"lastName\":\""+ lastName + "\",\n" +
                "  \"job\":{\n" +
                "    \"company\":\""+ jobCompany + "\",\n" +
                "    \"title\":\""+ jobTitle + "\"\n" +
                "  },\n" +
                "  \"family\":[\n" +
                "    {\n" +
                "      \"name\":\""+ familyName + "\"\n" +
                "    }\n" +
                "  ]\n" +
                "}"

        when: "I call POST on person"
        def response = mockMvc.perform(
                post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))

        then: "I expect a 201 response with the Person returned in the body"
        response
                .andExpect(status().isCreated())
                .andExpect(jsonPath('$.firstName').value(firstName))
                .andExpect(jsonPath('$.lastName').value(lastName))
                .andExpect(jsonPath('$.job.company').value(jobCompany))
                .andExpect(jsonPath('$.job.title').value(jobTitle))
                .andExpect(jsonPath('$.family[*].name').value(familyName))

        where:
        firstName | lastName | jobCompany    | jobTitle   | familyName
        "John"    | "Smith"  | "Capital One" | "Director" | "Smith"


    }

    def "When I try to create a invalid new person in the system I expect a 400 response with an error"() {
        given:
        weightCalculator.addItem(5)

        when: "add only one item"
        weightCalculator.addItem(5)

        then: "expect value of the item"
        weightCalculator.getTotalWeight() == 5
    }

}