package com.pearl.warehouse.dto.input;

import java.util.Date;

public record UpdatePatientInput(
        String medicalRecordNo,

        String name,

        Date dateOfBirth,

        String gender,

        String phoneNo,

        String email,

        String address,

        String emergencyContactName,

        String emergencyContactPhone

) {
}
