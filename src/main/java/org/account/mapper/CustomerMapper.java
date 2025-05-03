package org.account.mapper;

import org.account.dto.CustomerDto;
import org.account.entity.Customer;

public class CustomerMapper {

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmailId(customerDto.getName());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
    public static CustomerDto maptoCustomerDto(Customer customer,CustomerDto customerDto){
        customerDto.setName(customer.getName());
        customerDto.setEmail(customerDto.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

}
