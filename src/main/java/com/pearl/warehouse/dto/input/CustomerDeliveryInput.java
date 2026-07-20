package com.pearl.warehouse.dto.input;

import java.util.List;

public record CustomerDeliveryInput(
        String name,
        String email,
        String phone,
        List<DeliveryAddressInput> deliveryAddresses
) {}
