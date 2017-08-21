package com.example.unitTests.web.rest.models;

import com.example.unitTests.service.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class PersonView {

    private String firstName;

    private String lastName;

    private JobView job;

    private List<FamilyView> family;

    public PersonView(Person person) {
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.job = new JobView(person.getJob());
        this.family = person.getFamily().stream().map(FamilyView::new).collect(Collectors.toList());
    }

    public Person toDomain() {
        return Person.builder()
                     .firstName(this.getFirstName())
                     .lastName(this.lastName)
                     .family(this.getFamily()
                                 .stream().map(FamilyView::toDomain).collect(Collectors.toList()))
                     .job(this.getJob().toDomain())
                .build();
    }

}
