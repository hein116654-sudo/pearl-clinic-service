package com.pearl.warehouse.service;

import com.pearl.warehouse.dto.input.CreateSpecializationInput;
import com.pearl.warehouse.dto.response.SpecializationResponse;
import com.pearl.warehouse.exceptions.ResourceAlreadyExistsException;
import com.pearl.warehouse.exceptions.ResourceNotFoundException;
import com.pearl.warehouse.mapper.SpecializationMapper;
import com.pearl.warehouse.model.Specialization;
import com.pearl.warehouse.repository.PractitionerRepository;
import com.pearl.warehouse.repository.SpecializationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecializationService {
    private final SpecializationRepository specializationRepository;
    private final SpecializationMapper mapper;
    private final PractitionerRepository practitionerRepository;

    public List<SpecializationResponse> getAll(){
        return specializationRepository
                .findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }


    public SpecializationResponse createSpecialization(CreateSpecializationInput input) throws ResourceAlreadyExistsException{
        if (specializationRepository.existsByName(input.name())) {
            throw new ResourceAlreadyExistsException("Specialization already exists.");
        }
        Specialization specialization=mapper.toEntity(input);
        specialization = specializationRepository.save(specialization);

        return mapper.toResponse(specialization);
    }

    public SpecializationResponse getSpecializationById(Long id){
        Specialization optionalSpecialization= specializationRepository.findById(id).orElseThrow();
        return mapper.toResponse(optionalSpecialization);

    }

    public SpecializationResponse updateSpecialization(Long id, CreateSpecializationInput input) throws ResourceAlreadyExistsException{

        Specialization optionalSpecialization = specializationRepository.findById(id).orElseThrow();
        if(specializationRepository.existsByName(input.name())){
            throw new ResourceAlreadyExistsException("Specialization Already Exists!!");
        }
        optionalSpecialization.setName(input.name());
        specializationRepository.save(optionalSpecialization);
        return mapper.toResponse(optionalSpecialization);
    }

    public void delete(Long id) {
        Specialization specialization = specializationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specialization not found"));

        if (practitionerRepository.existsBySpecializationId(id)) {
            throw new IllegalStateException(
                    "Cannot delete specialization because it is assigned to practitioners.");
        }

        specializationRepository.delete(specialization);
    }
    public List<SpecializationResponse> searchByName(String name) {
        return specializationRepository
                .findByNameContainingIgnoreCase(name)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
