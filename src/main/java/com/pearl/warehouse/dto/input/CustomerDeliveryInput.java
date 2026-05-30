package com.pearl.warehouse.dto.input;

import java.util.List;

public record CustomerDeliveryInput(
        Long id,
        String name,
        String email,
        String phone,
        List<DeliveryAddressInput> deliveryAddresses
) {}
