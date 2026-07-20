package com.pearl.warehouse.controller;

import com.pearl.warehouse.dto.input.CreatePatientInput;
import com.pearl.warehouse.dto.input.UpdatePatientInput;
import com.pearl.warehouse.dto.response.PatientResponse;
import com.pearl.warehouse.mapper.PatientMapper;
import com.pearl.warehouse.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;
    private final PatientMapper patientMapper;
    @PostMapping("/create")
    public ResponseEntity<PatientResponse> create(@RequestBody CreatePatientInput input){

       PatientResponse patient = patientService.create(input);

        return ResponseEntity.ok(patient);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PatientResponse>> getAll(){
        return ResponseEntity.ok(patientService.getAll());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<PatientResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PatientResponse> updatePatient(@PathVariable Long id, @RequestBody UpdatePatientInput input){
        return ResponseEntity.ok(patientService.updatePatient(id,input));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

}
