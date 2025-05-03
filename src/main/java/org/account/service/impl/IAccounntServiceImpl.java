package org.account.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.account.dto.AccountsDto;
import org.account.dto.CustomerDto;
import org.account.entity.Accounts;
import org.account.entity.Customer;
import org.account.exception.CustomerAlreadyExistException;
import org.account.exception.ResourceNotFOundException;
import org.account.mapper.AccountMapper;
import org.account.mapper.CustomerMapper;
import org.account.repository.AccountRepository;
import org.account.repository.CustomerRepository;
import org.account.service.IAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class IAccounntServiceImpl implements IAccountService {

    private AccountRepository accountRepository;

    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {

        Customer customer= CustomerMapper.mapToCustomer(customerDto,new Customer());
       Optional<Customer> customerOptional= customerRepository.findByMobileNumber(customerDto.getMobileNumber());
       if (customerOptional.isPresent()){
           throw new CustomerAlreadyExistException("Customer already exist with mobile number"+customerDto.getMobileNumber());
       }
       customer.setCreatedBy("Anonymous");
       customer.setCreatedAt(LocalDateTime.now());
       Customer savedCustomer=customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerDto fetchAccountDetails(String mobileNumber) {
        Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()->new ResourceNotFOundException("Customer not found with this :"+mobileNumber));
        Accounts account = accountRepository.findById(customer.getCustomerId()).orElseThrow(()->new ResourceNotFOundException("Account not found with this :"+customer.getCustomerId()));
        CustomerDto customerDto=CustomerMapper.maptoCustomerDto(customer,new CustomerDto());
        customerDto.setAccountsDto(AccountMapper.mapToAccountsDto(account,new AccountsDto()));
        return customerDto;
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount=new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccountNumber=(long) (Math.random()*9000000000L)+1000000000L;
        newAccount.setAccountNumber(randomAccountNumber);
        newAccount.setAccountType("Savings");
        newAccount.setBranchAddress("Pune");
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");
        return newAccount;
    }
}
