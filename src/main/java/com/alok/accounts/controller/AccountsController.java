package com.alok.accounts.controller;

import com.alok.accounts.adaptor.AccountAdaptor;
import com.alok.accounts.model.AccountsDto;
import com.alok.accounts.model.CustomerDto;
import com.alok.accounts.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountsController {

    @Autowired
    private AccountsService  accountsService;

    @Autowired
    private AccountAdaptor accountAdaptor;

    @GetMapping("/myAccount/{customerId}")
    public ResponseEntity<AccountsDto> getAccountDetails(@PathVariable int customerId) {

       return new ResponseEntity<>(accountAdaptor.adapt(accountsService.getAccountDetails(customerId)), HttpStatus.OK);
    }
}
