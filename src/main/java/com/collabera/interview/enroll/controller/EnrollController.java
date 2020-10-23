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

import javax.websocket.server.PathParam;
import java.util.List;

import static java.lang.String.format;

@Slf4j
@RestController
@RequestMapping("api/enroll")
@RequiredArgsConstructor
public class EnrollController {

    private final EnrollService enrollService;

    @PostMapping("")
    public ResponseEntity<String> addEnroll(@RequestBody Enroll enroll){
        enrollService.saveEnroll(enroll);
        return new ResponseEntity<>(format("%s", enroll), HttpStatus.OK);
    }


    @GetMapping("")
    public ResponseEntity<List<Enroll>> getAllEnrollees(){
        return new ResponseEntity<>(enrollService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
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

    @PutMapping("")
    public ResponseEntity<String> updateEnroll(@PathParam("id") String id, @RequestBody Enroll enroll){
        enroll.setId(id);
        enrollService.saveEnroll(enroll);
        return new ResponseEntity<>(format("%s", enroll), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<String> removeEnroll(@PathParam(value = "id") String id){
        enrollService.deleteEnroll(id);
        return new ResponseEntity<>(format("%s", id), HttpStatus.OK);
    }

    @PutMapping("/dependent")
    public ResponseEntity addDependentToEnroll(@PathParam(value = "enrollId") String enrollId, @RequestBody List<Dependent> dependentList){

        return enrollId ==null? new ResponseEntity("ID should not be null ", HttpStatus.NOT_FOUND): new ResponseEntity(enrollService.saveDependents(enrollId,dependentList), HttpStatus.OK);
    }

    @DeleteMapping("/dependent")
    public ResponseEntity<String> deleteDependentPer(@PathParam("dependentId") String dependentId, @PathParam(value = "enrollId") String enrollId){
        enrollService.deleteDependentByEnrollId(dependentId,enrollId);
        return new ResponseEntity<>(format("Dependent with ID %s deleted ", dependentId), HttpStatus.OK);
    }

    @PutMapping("/dependent/modify")
    public ResponseEntity modifyDependentInEnroll(@RequestBody Dependent dependent, @PathParam (value = "enrollId") String enrollId){

        return new ResponseEntity<>( enrollService.modifyDependent(dependent,enrollId), HttpStatus.OK);
    }
}
