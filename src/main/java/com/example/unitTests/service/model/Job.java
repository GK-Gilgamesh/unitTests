package com.example.unitTests.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Job {
    private long id;

    private String company;

    private String title;

}
