package com.pearl.warehouse.controller;

import com.pearl.warehouse.dto.input.CreatePatientAllergyInput;
import com.pearl.warehouse.dto.input.UpdatePatientAllergyInput;
import com.pearl.warehouse.dto.response.PatientAllergyResponse;
import com.pearl.warehouse.mapper.PatientAllergyMapper;
import com.pearl.warehouse.model.PatientAllergy;
import com.pearl.warehouse.service.PatientAllergyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patientAllergies")
public class PatientAllergyController {
    private final PatientAllergyService patientAllergyService;
    private final PatientAllergyMapper patientAllergyMapper;
    @GetMapping("/getAll")
    public List<PatientAllergyResponse> getAll(){
        return patientAllergyService.getAllAllergy();
    }

    @PostMapping("/create")
    public ResponseEntity<PatientAllergyResponse> create(@RequestBody CreatePatientAllergyInput input){
       PatientAllergyResponse allergen= patientAllergyService.createPatientAllergy(input);
       return ResponseEntity.ok(allergen);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<PatientAllergyResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(patientAllergyService.getById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PatientAllergyResponse> update(@PathVariable Long id, @RequestBody UpdatePatientAllergyInput input){
        return ResponseEntity.ok(patientAllergyService.updatePatientAllergy(id,input));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        patientAllergyService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
