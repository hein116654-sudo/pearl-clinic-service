package com.pearl.warehouse.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddressResponse {

    private Long id;
    private String name;
    private String buildingNo;
    private String street;
}