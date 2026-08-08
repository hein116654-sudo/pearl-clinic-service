package com.pearl.warehouse.mapper;

import com.pearl.warehouse.dto.input.CreateSpecializationInput;
import com.pearl.warehouse.dto.response.SpecializationResponse;
import com.pearl.warehouse.model.Specialization;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface SpecializationMapper {
     Specialization toEntity(CreateSpecializationInput input);
     SpecializationResponse toResponse(Specialization entity);

}
