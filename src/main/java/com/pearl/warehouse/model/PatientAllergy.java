package com.pearl.warehouse.model;

import com.pearl.warehouse.exceptions.enums.Allergen;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient_allergies")

public class PatientAllergy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    private String allergenName;

    @Enumerated(EnumType.STRING)
    private Allergen status;
    @Column(name="drug_family")
    private String drugFamily;

    private String reaction;

    private OffsetDateTime recordedAt;
}
