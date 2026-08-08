package com.pearl.warehouse.service;

import com.pearl.warehouse.dto.input.CreatePatientAllergyInput;
import com.pearl.warehouse.dto.input.UpdatePatientAllergyInput;
import com.pearl.warehouse.dto.response.PatientAllergyResponse;
import com.pearl.warehouse.exceptions.ResourceNotFoundException;
import com.pearl.warehouse.mapper.PatientAllergyMapper;
import com.pearl.warehouse.model.Patient;
import com.pearl.warehouse.model.PatientAllergy;
import com.pearl.warehouse.repository.PatientAllergyRepository;
import com.pearl.warehouse.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientAllergyService {
    private final PatientAllergyRepository patientAllergyRepository;
    private final PatientAllergyMapper patientAllergyMapper;
    private final PatientRepository patientRepository;

    public PatientAllergyResponse createPatientAllergy(CreatePatientAllergyInput input){
        PatientAllergy patientAllergy=patientAllergyMapper.toEntity(input);

        Patient patient= patientRepository.findById(input.patientId()).orElseThrow(()->new ResourceNotFoundException("Patient Not Found!!"));
        patientAllergy.setPatient(patient);
        patientAllergy.setRecordedAt(OffsetDateTime.now());
        patientAllergyRepository.save(patientAllergy);
        return patientAllergyMapper.toResponse(patientAllergy);
    }

    public List<PatientAllergyResponse> getAllAllergy(){
        return patientAllergyRepository.findAll()
                .stream().map(patientAllergyMapper::toResponse).toList();
    }

    public PatientAllergyResponse updatePatientAllergy(Long id,UpdatePatientAllergyInput input){
        PatientAllergy patientAllergy=patientAllergyRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Patient Allergen Not Found!!"));
        patientAllergyMapper.updatePatientAllergyFromDto(input,patientAllergy);
        Patient patient = patientRepository.findById(input.patientId()).orElseThrow(()->new ResourceNotFoundException("Patient Not Found"));

        patientAllergy.setPatient(patient);
        patientAllergyRepository.save(patientAllergy);

        return patientAllergyMapper.toResponse(patientAllergy);
    }

    public PatientAllergyResponse getById(Long id){
        PatientAllergy patientAllergy=patientAllergyRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Patient Allergen Not Found"));
        return patientAllergyMapper.toResponse(patientAllergy);
    }

    public void delete(Long id){
        PatientAllergy patientAllergy=patientAllergyRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Patient Allergen Not Found"));
        patientAllergyRepository.delete(patientAllergy);
    }
}
