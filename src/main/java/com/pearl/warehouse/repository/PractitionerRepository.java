package com.pearl.warehouse.repository;

import com.pearl.warehouse.model.Practitioner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PractitionerRepository extends JpaRepository<Practitioner,Long> {
    public boolean  existsBySpecializationId(Long id);
}
