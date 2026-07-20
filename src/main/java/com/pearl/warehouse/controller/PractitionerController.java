package com.pearl.warehouse.controller;

import com.pearl.warehouse.dto.input.CreatePractitionerInput;
import com.pearl.warehouse.dto.input.UpdatePractitionerInput;
import com.pearl.warehouse.dto.response.PractitionerResponse;
import com.pearl.warehouse.service.PractitionerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequiredArgsConstructor
@RequestMapping("/practitioners")
public class PractitionerController {

    private final PractitionerService practitionerService;

    @PostMapping("/create")
    public ResponseEntity<PractitionerResponse> create(
            @RequestBody CreatePractitionerInput input) {

        PractitionerResponse response = practitionerService.create(input);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/list")
    public ResponseEntity<List<PractitionerResponse>> getAll() {

        List<PractitionerResponse> response = practitionerService.getAll();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<PractitionerResponse> getById(
            @PathVariable Long id) {

        PractitionerResponse response = practitionerService.getById(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<PractitionerResponse> update(
            @PathVariable Long id,
            @RequestBody UpdatePractitionerInput input) {

        PractitionerResponse response = practitionerService.update(id, input);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        practitionerService.delete(id);

        return ResponseEntity.noContent().build();
    }
}