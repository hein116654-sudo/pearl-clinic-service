package com.pearl.warehouse.dto.response;

import com.pearl.warehouse.exceptions.enums.Allergen;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientAllergyResponse {
    private Long id;
    private Long patientId;
    private String patientName;
    private String allergenName;
    private Allergen status;
    private String drugFamily;
    private String reaction;
    private OffsetDateTime recordedAt;


}
