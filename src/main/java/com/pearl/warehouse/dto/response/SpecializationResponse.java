package com.pearl.warehouse.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class SpecializationResponse {
    private Long id;
    private String name;
    private String description;
    private String status;
}
