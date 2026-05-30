package com.pearl.warehouse.mapper;

import com.pearl.warehouse.dto.input.CustomerDeliveryInput;
import com.pearl.warehouse.dto.response.CustomerDeliveryResponse;
import com.pearl.warehouse.model.CustomerDelivery;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = DeliveryAddressMapper.class)
public interface CustomerDeliveryMapper {

    CustomerDelivery toEntity(CustomerDeliveryInput input);

    CustomerDeliveryResponse toResponse(CustomerDelivery entity);
}