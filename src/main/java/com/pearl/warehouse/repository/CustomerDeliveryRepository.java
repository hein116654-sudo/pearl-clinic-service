package com.pearl.warehouse.repository;

import com.pearl.warehouse.model.CustomerDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDeliveryRepository extends JpaRepository<CustomerDelivery, Long> {
}
