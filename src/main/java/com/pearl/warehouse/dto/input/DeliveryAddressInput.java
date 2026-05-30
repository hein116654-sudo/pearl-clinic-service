package com.pearl.warehouse.dto.input;

public record DeliveryAddressInput(
        Long id,
        String name,
        String buildingNo,
        String street
) {}
