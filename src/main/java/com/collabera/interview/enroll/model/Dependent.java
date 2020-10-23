package com.collabera.interview.enroll.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
//@NoArgsConstructor
@AllArgsConstructor
@Document
public class Dependent {

    @Id
    private String id;
    private String name;
    private LocalDate dateOfBirth;



    public Dependent() {
        this.id = UUID.randomUUID().toString();
    }
}
