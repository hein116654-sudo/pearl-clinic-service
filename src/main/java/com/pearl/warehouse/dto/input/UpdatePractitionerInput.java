package com.pearl.warehouse.dto.input;

public record UpdatePractitionerInput (


        String name,

        String licenseNumber,

        String email,

        String phone,

        String status,

        Long specializationId

){
}
