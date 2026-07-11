package com.pearl.warehouse.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "patients")
@AllArgsConstructor
@NoArgsConstructor

public class Patient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String medicalRecordNo;

    private String name;

    private Date dateOfBirth;

    private String gender;

    private String phoneNo;

    private String email;

    private String address;

    private String emergencyContactName;

    private String emergencyContactPhone;

    private OffsetDateTime createdAt;


}
