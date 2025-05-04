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
        Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()->new ResourceNotFOundException("Customer","mobileNumber",mobileNumber));
        Accounts account = accountRepository.findById(customer.getCustomerId()).orElseThrow(()->new ResourceNotFOundException("Account","customerId",customer.getCustomerId().toString()));
        CustomerDto customerDto=CustomerMapper.maptoCustomerDto(customer,new CustomerDto());
        customerDto.setAccountsDto(AccountMapper.mapToAccountsDto(account,new AccountsDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccountDetails(CustomerDto customerDto) {
        boolean isupdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        Accounts account = null;
        if (accountsDto != null) {
            account = accountRepository.findById(accountsDto.getAccountNumber())
                    .orElseThrow(() -> new ResourceNotFOundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString()));

            AccountMapper.mapToAccounts(account, accountsDto);
            accountRepository.save(account);
            Long customerId = account.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFOundException("Customer", "CustomerId", customerId.toString()));
            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);
            isupdated = true;
        }
        return isupdated;
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
