package com.pearl.warehouse.mapper;

import com.pearl.warehouse.dto.input.DeliveryAddressInput;
import com.pearl.warehouse.dto.response.DeliveryAddressResponse;
import com.pearl.warehouse.model.DeliveryAddress;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeliveryAddressMapper {

    DeliveryAddress toEntity(DeliveryAddressInput input);

    DeliveryAddressResponse toResponse(DeliveryAddress entity);
}