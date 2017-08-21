package com.example.unitTests.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class Person {

    private long id;

    private String firstName;

    private String lastName;

    private Job job;

    private List<Family> family;
}
