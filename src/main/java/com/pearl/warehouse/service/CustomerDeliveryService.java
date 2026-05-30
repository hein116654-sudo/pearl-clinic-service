package com.pearl.warehouse.service;

import com.pearl.warehouse.dto.input.CustomerDeliveryInput;
import com.pearl.warehouse.dto.input.DeliveryAddressInput;
import com.pearl.warehouse.dto.response.CustomerDeliveryResponse;
import com.pearl.warehouse.dto.response.DeliveryAddressResponse;
import com.pearl.warehouse.mapper.CustomerDeliveryMapper;
import com.pearl.warehouse.mapper.DeliveryAddressMapper;
import com.pearl.warehouse.model.CustomerDelivery;
import com.pearl.warehouse.model.DeliveryAddress;
import com.pearl.warehouse.repository.CustomerDeliveryRepository;
import com.pearl.warehouse.repository.DeliveryAddressRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerDeliveryService {

    private final CustomerDeliveryRepository customerDeliveryRepository;
    private final DeliveryAddressRepository deliveryAddressRepository;
    private final CustomerDeliveryMapper customerDeliveryMapper;
    private final DeliveryAddressMapper deliveryAddressMapper;

    // CREATE
    @Transactional
    public CustomerDeliveryResponse saveCustomerDelivery(CustomerDeliveryInput input) {

        CustomerDelivery customer =
                customerDeliveryMapper.toEntity(input);

        if (customer.getDeliveryAddresses() != null) {
            customer.getDeliveryAddresses().forEach(addr ->
                    addr.setCustomerDelivery(customer)
            );
        }

        return customerDeliveryMapper.toResponse(
                customerDeliveryRepository.save(customer)
        );
    }

    // LIST
    public List<CustomerDeliveryResponse> getAllCustomers() {

        return customerDeliveryRepository.findAll()
                .stream()
                .map(customerDeliveryMapper::toResponse)
                .toList();
    }

    // DETAIL
    public CustomerDeliveryResponse findById(Long id) {

        CustomerDelivery customer =
                customerDeliveryRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Customer not found"));

        return customerDeliveryMapper.toResponse(customer);
    }

    // DELETE CUSTOMER
    @Transactional
    public Boolean deleteByCustomerId(Long id) {

        if (!customerDeliveryRepository.existsById(id)) {
            return false;
        }

        customerDeliveryRepository.deleteById(id);
        return true;
    }

    // DELETE ADDRESSES
    @Transactional
    public Boolean deleteByDeliveryId(Long id) {

        if (!deliveryAddressRepository.existsById(id)) {
            return false;
        }

        deliveryAddressRepository.deleteById(id);

        return true;
    }

    // UPDATE
    @Transactional
    public CustomerDeliveryResponse update(Long id, CustomerDeliveryInput input) {

        CustomerDelivery customer =
                customerDeliveryRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setName(input.name());
        customer.setEmail(input.email());
        customer.setPhone(input.phone());

        customer.getDeliveryAddresses().clear();

        if (input.deliveryAddresses() != null) {

            List<DeliveryAddress> addresses =
                    input.deliveryAddresses()
                            .stream()
                            .map(deliveryAddressMapper::toEntity)
                            .toList();

            addresses.forEach(a -> a.setCustomerDelivery(customer));

            customer.getDeliveryAddresses().addAll(addresses);
        }

        return customerDeliveryMapper.toResponse(
                customerDeliveryRepository.save(customer)
        );
    }

    // ADD ADDRESS
    @Transactional
    public CustomerDeliveryResponse addAddress(Long customerId, DeliveryAddressInput input) {

        CustomerDelivery customer =
                customerDeliveryRepository.findById(customerId)
                        .orElseThrow(() -> new RuntimeException("Customer not found"));

        DeliveryAddress address =
                deliveryAddressMapper.toEntity(input);

        address.setCustomerDelivery(customer);

        customer.getDeliveryAddresses().add(address);

        return customerDeliveryMapper.toResponse(
                customerDeliveryRepository.save(customer)
        );
    }
}