package com.exercise.bank_api.service;

import com.exercise.bank_api.dto.CustomerDetailsDto;

public interface CustomerService {
    CustomerDetailsDto getCustomerDetails(Long customerId);
}
