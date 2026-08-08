package com.pearl.warehouse.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
public class PractitionerResponse {
    private Long id;
    private String name;
    private String licenseNumber;
    private String email;
    private String phone;
    private String status;
    private String specializationName;
    private OffsetDateTime createdAt;


}
