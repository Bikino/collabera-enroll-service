package com.collabera.interview.enroll.service;

import com.collabera.interview.enroll.model.Dependent;
import com.collabera.interview.enroll.model.Enroll;

import java.util.List;

public interface EnrollService {
    void saveEnroll(Enroll enroll);
    List<Enroll> findAll();
    Enroll findById(String id);
    void deleteEnroll(String id);
    void saveDependents(String enrollId, List<Dependent> dependentList);
    void deleteDependentByEnrollId(String dependentId, String enrollId);
    void modifyDependent(Dependent dependent, String enrollId);

}
