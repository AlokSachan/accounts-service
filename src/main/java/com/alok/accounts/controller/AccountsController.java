package com.alok.accounts.controller;

import com.alok.accounts.adaptor.AccountAdaptor;
import com.alok.accounts.config.AccountsServiceConfig;
import com.alok.accounts.model.AccountsDto;
import com.alok.accounts.model.Properties;
import com.alok.accounts.service.AccountsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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

    @Autowired
    private AccountsServiceConfig accountsServiceConfig;

    @GetMapping("/myAccount/{customerId}")
    public ResponseEntity<AccountsDto> getAccountDetails(@PathVariable int customerId) {

       return new ResponseEntity<>(accountAdaptor.adapt(accountsService.getAccountDetails(customerId)), HttpStatus.OK);
    }

    @GetMapping("/myAccount/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow= new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties= Properties.builder().msg(accountsServiceConfig.getMsg())
                .buildVersion(accountsServiceConfig.getBuildVersion()).mailDetails(accountsServiceConfig.getMailDetails())
                .activeBranches(accountsServiceConfig.getActiveBranches()).build();
        String jsonStr = ow.writeValueAsString(properties);
        return jsonStr;
    }
}
