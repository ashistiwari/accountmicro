package org.account.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.account.dto.CustomerDto;
import org.account.entity.Customer;
import org.account.mapper.CustomerMapper;
import org.account.repository.AccountRepository;
import org.account.repository.CustomerRepository;
import org.account.service.IAccountService;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class IAccounntServiceImpl implements IAccountService {

    private AccountRepository accountRepository;

    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {

        Customer customer= CustomerMapper.mapToCustomer(customerDto,new Customer());

    }
}
