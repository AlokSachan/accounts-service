package com.alok.accounts.controller;

import com.alok.accounts.adaptor.AccountAdaptor;
import com.alok.accounts.config.AccountsServiceConfig;
import com.alok.accounts.model.AccountsDto;
import com.alok.accounts.model.CustomerDetailsDto;
import com.alok.accounts.model.Properties;
import com.alok.accounts.service.AccountsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
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

    @GetMapping(value = "/myAccount/customerDetails/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    //@CircuitBreaker(name = "customerDetailsSupportApp", fallbackMethod = "getCustomerDetailsFallBack")
   // @Retry(name = "retryForCustomerDetails", fallbackMethod = "getCustomerDetailsFallBack")
    @Retry(name = "retryForCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> getCustomerDetails(@PathVariable Integer customerId,
                                                                 @RequestHeader("eazybank-correlation-id")  String correlationId){
        log.info("correlationId : {}", correlationId);
        return new ResponseEntity<>(accountsService.customerDetails(customerId, correlationId), HttpStatus.OK);
    }

    private ResponseEntity<CustomerDetailsDto> getCustomerDetailsFallBack(Integer customerId, Throwable th,
                                                                          @RequestHeader("eazybank-correlation-id")  String correlationId){
        log.info("inside Fall Back Method");
        return new ResponseEntity<>(accountsService.getCustomerDetailsFallBack(customerId, correlationId), HttpStatus.OK);
    }

    @GetMapping("/sayHello")
    @RateLimiter(name = "sayHello", fallbackMethod = "sayHelloFallback")
    public String sayHello() {
        return "Hello, Welcome to EazyBank";
    }

    private String sayHelloFallback(Throwable t) {
        return "Hi, Welcome to EazyBank";
    }

}
