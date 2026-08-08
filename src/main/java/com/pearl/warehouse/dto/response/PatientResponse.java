package com.pearl.warehouse.dto.response;

import java.time.OffsetDateTime;
import java.util.Date;
public record PatientResponse(

        Long id,
        String medicalRecordNo,
        String name,
        Date dateOfBirth,
        String gender,
        String phoneNo,
        String email,
        String address,
        String emergencyContactName,
        String emergencyContactPhone,
        OffsetDateTime createdAt

) {
}