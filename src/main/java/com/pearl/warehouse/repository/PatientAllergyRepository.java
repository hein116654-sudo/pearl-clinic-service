package com.pearl.warehouse.repository;

import com.pearl.warehouse.model.PatientAllergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientAllergyRepository extends JpaRepository<PatientAllergy,Long> {
    public boolean existsByPatientId(Long id);
}
