package com.alok.accounts.adaptor;

import com.alok.accounts.entity.Accounts;
import com.alok.accounts.model.AccountsDto;
import org.springframework.stereotype.Component;

@Component
public class AccountAdaptor {
    public AccountsDto adapt(Accounts accounts) {
        return AccountsDto.builder()
                .customerId(accounts.getCustomerId())
                .accountNumber(accounts.getAccountNumber())
                .accountType(accounts.getAccountType())
                .branchAddress(accounts.getBranchAddress())
                .createDt(accounts.getCreateDt()).build();
    }
}
