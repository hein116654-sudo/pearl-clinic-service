package com.pearl.warehouse.repository;


import com.pearl.warehouse.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface SpecializationRepository extends JpaRepository<Specialization,Long> {
    boolean existsByName(String name);
   Optional<Specialization> searchByName(String name);
    List<Specialization> findByNameContainingIgnoreCase(String name);
}
