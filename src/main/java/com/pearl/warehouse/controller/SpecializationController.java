package com.pearl.warehouse.controller;

import com.pearl.warehouse.dto.input.CreateSpecializationInput;
import com.pearl.warehouse.dto.response.SpecializationResponse;
import com.pearl.warehouse.service.SpecializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/specializations")
public class SpecializationController {
    private final SpecializationService specializationService;
    @GetMapping("/list")
    public ResponseEntity<List<SpecializationResponse>> getAll(){
       return  ResponseEntity.ok(specializationService.getAll());
    }

    @PostMapping("/save")
    public ResponseEntity<SpecializationResponse> createSpecialization(@RequestBody CreateSpecializationInput input){
       SpecializationResponse specialization= specializationService.createSpecialization(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(specialization);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SpecializationResponse> updateSpecialization( @PathVariable Long id,@RequestBody CreateSpecializationInput input){
        SpecializationResponse updateSpecialization =specializationService.updateSpecialization(id,input);
        return ResponseEntity.ok(updateSpecialization);
    }

    @GetMapping("/searchByName")
    public List<SpecializationResponse> searchByName(@RequestParam String name){
        return specializationService.searchByName(name);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        specializationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
