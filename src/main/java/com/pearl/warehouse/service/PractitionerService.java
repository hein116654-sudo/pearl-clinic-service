package com.pearl.warehouse.service;

import com.pearl.warehouse.dto.input.CreatePractitionerInput;
import com.pearl.warehouse.dto.input.UpdatePractitionerInput;
import com.pearl.warehouse.dto.response.PractitionerResponse;
import com.pearl.warehouse.exceptions.ResourceNotFoundException;
import com.pearl.warehouse.mapper.PractitionerMapper;
import com.pearl.warehouse.model.Practitioner;
import com.pearl.warehouse.model.Specialization;
import com.pearl.warehouse.repository.PractitionerRepository;
import com.pearl.warehouse.repository.SpecializationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PractitionerService {

    private final PractitionerRepository practitionerRepository;
    private final SpecializationRepository specializationRepository;
    private final PractitionerMapper practitionerMapper;

    public PractitionerResponse create(CreatePractitionerInput input) {

        Practitioner practitioner = practitionerMapper.toEntity(input);

        Specialization specialization = specializationRepository.findById(input.specializationId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Specialization not found"));

        practitioner.setSpecialization(specialization);
        practitioner.setCreatedAt(OffsetDateTime.now());

        practitionerRepository.save(practitioner);

        return practitionerMapper.toResponse(practitioner);
    }

    public List<PractitionerResponse> getAll() {

        return practitionerRepository.findAll()
                .stream()
                .map(practitionerMapper::toResponse)
                .toList();
    }

    public PractitionerResponse getById(Long id) {

        Practitioner practitioner = practitionerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Practitioner not found"));

        return practitionerMapper.toResponse(practitioner);
    }

    public PractitionerResponse update(Long id, UpdatePractitionerInput input) {

        Practitioner practitioner = practitionerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Practitioner not found"));

        practitionerMapper.updatePractitionerFromDto(input, practitioner);

        Specialization specialization = specializationRepository.findById(input.specializationId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Specialization not found"));

        practitioner.setSpecialization(specialization);

        practitionerRepository.save(practitioner);

        return practitionerMapper.toResponse(practitioner);
    }

    public void delete(Long id) {

        Practitioner practitioner = practitionerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Practitioner not found"));

        practitionerRepository.delete(practitioner);
    }
}