package com.pearl.warehouse.mapper;

import com.pearl.warehouse.dto.input.CreatePatientAllergyInput;
import com.pearl.warehouse.dto.input.UpdatePatientAllergyInput;
import com.pearl.warehouse.dto.response.PatientAllergyResponse;
import com.pearl.warehouse.model.PatientAllergy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel="spring")
public interface PatientAllergyMapper {
    @Mapping(target="id",ignore=true)
    @Mapping(target="patient",ignore=true)
    @Mapping(source = "drugFamily", target = "drugFamily")
    PatientAllergy toEntity(CreatePatientAllergyInput input);


    @Mapping(source="patient.name",target = "patientName")
    @Mapping(source="patient.id",target="patientId")
    PatientAllergyResponse toResponse(PatientAllergy entity);

    @Mapping(target="id",ignore=true)
    void updatePatientAllergyFromDto(
            UpdatePatientAllergyInput input,
            @MappingTarget PatientAllergy patientAllergy);
}
