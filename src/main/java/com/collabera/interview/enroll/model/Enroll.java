package com.collabera.interview.enroll.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Enroll {

    @Id
    private String id;
    private String name;
    private Boolean status;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private List<Dependent> dependents;

}
