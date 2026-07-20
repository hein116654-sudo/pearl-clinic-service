package com.pearl.warehouse.controller;

import com.pearl.warehouse.dto.input.CreateSpecializationInput;
import com.pearl.warehouse.dto.response.SpecializationResponse;
import com.pearl.warehouse.service.SpecializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/specializations")
public class SpecializationController {
    private final SpecializationService specializationService;
    @GetMapping("/list")
    public List<SpecializationResponse> getAll(){
       return  specializationService.getAll();
    }

    @PostMapping("/save")
    public SpecializationResponse createSpecialization(@RequestBody CreateSpecializationInput input){
        return specializationService.createSpecialization(input);
    }

    @PutMapping("/update/{id}")
    public SpecializationResponse updateSpecialization(@RequestBody CreateSpecializationInput input, @PathVariable Long id){
        return specializationService.updateSpecialization(id,input);
    }

    @GetMapping("/searchByName")
    public List<SpecializationResponse> searchByName(@RequestParam String name){
        return specializationService.searchByName(name);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        specializationService.delete(id);
        return ResponseEntity.ok("Specialization deleted successfully.");
    }
}
