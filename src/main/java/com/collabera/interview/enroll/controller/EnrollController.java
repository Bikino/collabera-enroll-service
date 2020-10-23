package com.collabera.interview.enroll.controller;

import com.collabera.interview.enroll.exception.EnrollNotFoundException;
import com.collabera.interview.enroll.model.Dependent;
import com.collabera.interview.enroll.model.Enroll;
import com.collabera.interview.enroll.service.EnrollService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.String.format;

@Slf4j
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class EnrollController {

    private final EnrollService enrollService;

    @PostMapping("/enroll")
    public ResponseEntity<String> addEnroll(@RequestBody Enroll enroll){
        enrollService.saveEnroll(enroll);
        return new ResponseEntity<>(format("%s", enroll), HttpStatus.OK);
    }


    @GetMapping("/enroll")
    public ResponseEntity<List<Enroll>> getAllEnrollees(){
        return new ResponseEntity<>(enrollService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/enroll/{id}")
    public ResponseEntity<Enroll> getOneEnrollee(@PathVariable(name = "id") String id){
        Enroll enroll;
        try {
            enroll = enrollService.findById(id);
            return new ResponseEntity<>(enroll, HttpStatus.OK);
        }catch (EnrollNotFoundException exception){
            log.error("!!!!! Enrollee with id {} not found", id);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/enroll/update/{id}")
    public ResponseEntity<String> updateEnroll(@PathVariable("id") String id, @RequestBody Enroll enroll){
        enroll.setId(id);
        enrollService.saveEnroll(enroll);
        return new ResponseEntity<>(format("%s", enroll), HttpStatus.OK);
    }

    @DeleteMapping("/enroll/delete/{id}")
    public ResponseEntity<String> removeEnroll(@PathVariable String id){
        enrollService.deleteEnroll(id);
        return new ResponseEntity<>(format("%s", id), HttpStatus.OK);
    }

    @PutMapping("/enroll/update/dependent/{id}")
    public ResponseEntity<String> addDependentToEnroll(@PathVariable String id, @RequestBody List<Dependent> dependentList){
        enrollService.saveDependents(id,dependentList);
        return new ResponseEntity<>(format("%s", dependentList), HttpStatus.OK);
    }

    @DeleteMapping("/enroll/delete/dependent/{dependentId}/{enrollId}")
    public ResponseEntity<String> deleteDependentPer(@PathVariable String dependentId, @PathVariable String enrollId){
        enrollService.deleteDependentByEnrollId(dependentId,enrollId);
        return new ResponseEntity<>(format("%s", dependentId), HttpStatus.OK);
    }

    @PutMapping("/enroll/update/dependent/{enrollId}")
    public ResponseEntity<String> modifyDependentInEnroll(Dependent dependent, String enrollId){
        enrollService.modifyDependent(dependent,enrollId);
        return new ResponseEntity<>(format("%s", dependent), HttpStatus.OK);
    }
}
