package com.pearl.warehouse.dto.input;

import com.pearl.warehouse.exceptions.enums.Allergen;

public record CreatePatientAllergyInput(
        Long patientId,
        String allergenName,
        Allergen status,
        String drugFamily,
        String reaction) {
}
