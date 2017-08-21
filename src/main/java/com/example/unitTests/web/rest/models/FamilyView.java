package com.example.unitTests.web.rest.models;

import com.example.unitTests.service.model.Family;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FamilyView {
    private String name;

    public FamilyView(Family family){
        this.name = family.getName();
    }

    public Family toDomain(){
        return Family.builder()
                     .name(this.getName())
                     .build();
    }
}
