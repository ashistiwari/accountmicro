package org.account.service;

import org.account.dto.CustomerDto;
import org.springframework.stereotype.Service;

@Service
public interface IAccountService {

    void createAccount(CustomerDto customerDto);
}
