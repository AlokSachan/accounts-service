package com.alok.accounts.service;


import com.alok.accounts.entity.Accounts;
import com.alok.accounts.model.CustomerDetailsDto;

public interface AccountsService {

    Accounts getAccountDetails(int customerId);

    CustomerDetailsDto customerDetails(Integer customerId);
}
