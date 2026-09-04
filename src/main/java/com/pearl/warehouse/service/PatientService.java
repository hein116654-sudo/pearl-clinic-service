package com.pearl.warehouse.service;

import com.pearl.warehouse.dto.input.CreatePatientInput;
import com.pearl.warehouse.dto.input.UpdatePatientInput;
import com.pearl.warehouse.dto.response.PatientResponse;
import com.pearl.warehouse.exceptions.ResourceNotFoundException;
import com.pearl.warehouse.mapper.PatientMapper;
import com.pearl.warehouse.model.Patient;
import com.pearl.warehouse.repository.PatientAllergyRepository;
import com.pearl.warehouse.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientAllergyRepository patientAllergyRepository;
    private final PatientMapper patientMapper;

    public List<PatientResponse> getAll(){
        return patientRepository.findAll().stream().map(patientMapper::toResponse).toList();
    }

    public PatientResponse create(CreatePatientInput input){
        Patient patient =patientMapper.toEntity(input);
        patient.setCreatedAt(OffsetDateTime.now());
        patientRepository.save(patient);
        return patientMapper.toResponse(patient);
    }

    public PatientResponse getPatientById(Long id){
        Patient patient= patientRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Patient Not Found!!"));
        return patientMapper.toResponse(patient);
    }
    @Transactional
    public PatientResponse updatePatient(Long id, UpdatePatientInput input){
    Patient patient = patientRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Patient Not Found!"));
    patientMapper.updatePatientFromDto(input,patient);
    patientRepository.save(patient);
        return patientMapper.toResponse(patient);
    }
    @Transactional
    public void deletePatient(Long id){

        Patient patient = patientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Patient Not Found"));
        if(patientAllergyRepository.existsByPatientId(id)){
            throw new IllegalStateException("Cannot delete Patient because it is assigned to PatientAllergy.");
        }
        patientRepository.delete(patient);
    }
}
