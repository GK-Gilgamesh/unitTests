package com.example.unitTests.web.rest.models;

import com.example.unitTests.service.model.Job;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobView {
    private String company;

    private String title;

    public JobView(Job job){
        this.company = job.getCompany();

        this.title = job.getTitle();
    }

    public Job toDomain(){
        return Job.builder()
                  .company(this.getCompany())
                  .title(this.getTitle())
                  .build();
    }
}
