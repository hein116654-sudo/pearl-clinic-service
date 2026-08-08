package com.pearl.warehouse.mapper;

import com.pearl.warehouse.dto.input.CreatePractitionerInput;
import com.pearl.warehouse.dto.input.UpdatePractitionerInput;
import com.pearl.warehouse.dto.response.PractitionerResponse;
import com.pearl.warehouse.model.Practitioner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PractitionerMapper {
    @Mapping(target="id",ignore = true)
    @Mapping(target="specialization",ignore = true)
    @Mapping(target="createdAt",ignore=true)
    Practitioner toEntity(CreatePractitionerInput input);

    @Mapping(source = "specialization.name",target = "specializationName")
    PractitionerResponse toResponse(Practitioner entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "specialization", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updatePractitionerFromDto(
            UpdatePractitionerInput input,
            @MappingTarget Practitioner practitioner
    );
}
