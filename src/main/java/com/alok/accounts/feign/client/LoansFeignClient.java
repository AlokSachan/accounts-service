package com.alok.accounts.feign.client;

import com.alok.accounts.feign.response.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "loans")
public interface LoansFeignClient {

@RequestMapping(method = RequestMethod.GET,path = "/myLoans/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
        List<LoansDto> getLoansDetails(@PathVariable int customerId);
}
