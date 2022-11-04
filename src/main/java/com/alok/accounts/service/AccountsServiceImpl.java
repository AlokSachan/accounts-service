package com.alok.accounts.service;

import com.alok.accounts.entity.Accounts;
import com.alok.accounts.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountsServiceImpl implements AccountsService {

    @Autowired
    private AccountsRepository accountsRepository;

    @Override
    public Accounts getAccountDetails(int customerId) {
      return Optional.ofNullable(accountsRepository.findByCustomerId(customerId))
               .orElse(new Accounts());
    }
}
