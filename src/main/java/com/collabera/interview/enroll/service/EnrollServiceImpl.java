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
        if (enrollRepository.existsById(id)) {
            this.enrollRepository.deleteById(id);
        }else {
            throw new EnrollNotFoundException(format("Enrollee with id %s not found ", id));
        }
    }

    @Override
    public Enroll saveDependents(String enrollId, List<Dependent> dependentList) {
        Enroll enroll = enrollRepository.findById(enrollId).orElseThrow(()->new EnrollNotFoundException(format("Enrollee with id %s not found", enrollId)));
        enroll.getDependents().addAll(dependentList);
        return enroll;
    }

    @Override
    public void deleteDependentByEnrollId(String dependentId, String enrollId) {
        Enroll enroll = enrollRepository.findById(enrollId).orElseThrow(()->new EnrollNotFoundException(format("Enrollee with id %s not found", enrollId)));
       List<Dependent>dependentList= enroll.getDependents();
       if (!dependentList.isEmpty()){
           dependentList.remove(dependentList.stream()
                   .filter(dependent -> dependent.getId().equals(dependentId)).findFirst().orElseThrow(()->new EnrollNotFoundException(format("dependent with id %s not found", dependentId))));
           enroll.setDependents(dependentList);
           enrollRepository.save(enroll);
       }

    }

    @Override
    public Enroll modifyDependent(Dependent dependent, String enrollId) {
        Enroll enroll = enrollRepository.findById(enrollId).orElseThrow(()->new EnrollNotFoundException(format("Enrollee with id %s not found", enrollId)));
        Dependent dependent1= enroll.getDependents().stream()
                .filter(dependent2 -> dependent2.getId().equals(dependent.getId()))
                .findFirst()
                .orElseThrow(()->new EnrollNotFoundException(format("dependent  with id %s not found", dependent.getId())));
        int dependentIndex= enroll.getDependents().indexOf(dependent1);
        dependent1.setDateOfBirth(dependent.getDateOfBirth());
        dependent1.setName(dependent.getName());
   List<Dependent>dependentList=    enroll.getDependents();
   dependentList.set(dependentIndex, dependent1);
   enroll.setDependents(dependentList);
        enrollRepository.save(enroll);
        return enroll;

    }


}
