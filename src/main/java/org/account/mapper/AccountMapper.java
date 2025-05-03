package org.account.mapper;

import org.account.dto.AccountsDto;
import org.account.entity.Accounts;

public class AccountMapper {

        public static AccountsDto mapToAccountsDto(Accounts accounts,AccountsDto accountsDto){
            accountsDto.setAccountNumber(accounts.getAccountNumber());
            accountsDto.setAccountType(accounts.getAccountType());
            accountsDto.setBranchAddress(accounts.getBranchAddress());
            return accountsDto;
        }

        public static Accounts mapToAccounts(Accounts accounts,AccountsDto accountsDto){
            accounts.setAccountNumber(accountsDto.getAccountNumber());
            accounts.setAccountType(accountsDto.getAccountType());
            accounts.setBranchAddress(accountsDto.getBranchAddress());
            return accounts;
        }
    }

