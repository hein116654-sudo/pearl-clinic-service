package com.pearl.warehouse.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerDeliveryResponse {

    private Long id;
    private String name;
    private String email;
    private String phone;

    private List<DeliveryAddressResponse> deliveryAddresses;
}