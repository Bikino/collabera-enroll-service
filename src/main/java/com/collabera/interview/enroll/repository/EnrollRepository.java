package com.collabera.interview.enroll.repository;

import com.collabera.interview.enroll.model.Enroll;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollRepository extends MongoRepository<Enroll, String> {
}
