package com.pearl.warehouse.controller;


import com.pearl.warehouse.dto.input.CustomerDeliveryInput;
import com.pearl.warehouse.dto.input.DeliveryAddressInput;
import com.pearl.warehouse.dto.response.CustomerDeliveryResponse;
import com.pearl.warehouse.model.CustomerDelivery;
import com.pearl.warehouse.service.CustomerDeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerDeliveryService customerDeliveryService;

    @PostMapping("/save")
    public CustomerDeliveryResponse saveCustomer(
            @RequestBody CustomerDeliveryInput input) {

        return customerDeliveryService.saveCustomerDelivery(input);
    }
    // LIST (table)
    @GetMapping("/list")
    public List<CustomerDeliveryResponse> getAllCustomers() {
        return customerDeliveryService.getAllCustomers();
    }

    // DETAIL
    @GetMapping("/detail/{id}")
    public CustomerDeliveryResponse getCustomerById(@PathVariable Long id) {
        return customerDeliveryService.findById(id);
    }
    @DeleteMapping("deleteByCustomer/{id}")
    public Boolean deleteCustomerDelivery(@PathVariable Long id) {
        return customerDeliveryService.deleteByCustomerId(id);
    }

    @DeleteMapping("deleteByDelivery/{id}")
    public Boolean deleteCustomerDeliveryByDelivery(@PathVariable Long id) {
        return customerDeliveryService.deleteByDeliveryId(id);
    }


    // UPDATE
    @PutMapping("/update/{id}")
    public CustomerDeliveryResponse updateCustomerDelivery(
            @PathVariable Long id,
            @RequestBody CustomerDeliveryInput input) {

        return customerDeliveryService.update(id, input);
    }

    // ADD ADDRESS
    @PostMapping("/{customerId}/add-address")
    public CustomerDeliveryResponse addAddress(
            @PathVariable Long customerId,
            @RequestBody DeliveryAddressInput input) {

        return customerDeliveryService.addAddress(customerId, input);
    }
}