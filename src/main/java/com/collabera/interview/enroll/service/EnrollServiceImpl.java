package com.collabera.interview.enroll.service;

import com.collabera.interview.enroll.exception.EnrollNotFoundException;
import com.collabera.interview.enroll.model.Dependent;
import com.collabera.interview.enroll.model.Enroll;
import com.collabera.interview.enroll.repository.EnrollRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnrollServiceImpl implements EnrollService{

    private final EnrollRepository enrollRepository;

    @Override
    public void saveEnroll(Enroll enroll) {
        this.enrollRepository.save(enroll);
    }

    @Override
    public List<Enroll> findAll() {
        return this.enrollRepository.findAll();
    }

    @Override
    public Enroll findById(String id) {
        return this.enrollRepository.findById(id)
                .orElseThrow(() -> new EnrollNotFoundException(format("Enrollee with id %s not found", id)));
    }

    @Override
    public void deleteEnroll(String id) {
        this.enrollRepository.deleteById(id);
    }

    @Override
    public void saveDependents(String enrollId, List<Dependent> dependentList) {
        Enroll enroll = enrollRepository.findById(enrollId).get();
        enroll.getDependants().addAll(dependentList);
    }

    @Override
    public void deleteDependentByEnrollId(String dependentId, String enrollId) {
        Enroll enroll = enrollRepository.findById(enrollId).get();
        enroll.getDependants().remove(enroll);
    }

    @Override
    public void modifyDependent(Dependent dependent, String enrollId) {
        Enroll enroll = enrollRepository.findById(enrollId).get();
         enroll.getDependants().add(dependent);
    }


}
