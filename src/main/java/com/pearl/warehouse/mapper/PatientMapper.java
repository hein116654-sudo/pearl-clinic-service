package com.pearl.warehouse.mapper;


import com.pearl.warehouse.dto.input.CreatePatientInput;
import com.pearl.warehouse.dto.input.UpdatePatientInput;
import com.pearl.warehouse.dto.response.PatientResponse;
import com.pearl.warehouse.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    Patient toEntity(CreatePatientInput input);

    PatientResponse toResponse(Patient entity);

    @Mapping(target="id",ignore = true)
    @Mapping(target="createdAt",ignore = true)
    void updatePatientFromDto(
            UpdatePatientInput updatePatientInput,
            @MappingTarget Patient patient);
}

