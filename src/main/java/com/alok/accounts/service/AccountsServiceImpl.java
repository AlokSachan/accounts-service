package com.alok.accounts.service;

import com.alok.accounts.adaptor.AccountAdaptor;
import com.alok.accounts.entity.Accounts;
import com.alok.accounts.feign.client.CardsFeignClient;
import com.alok.accounts.feign.client.LoansFeignClient;
import com.alok.accounts.feign.response.CardsDto;
import com.alok.accounts.feign.response.LoansDto;
import com.alok.accounts.model.AccountsDto;
import com.alok.accounts.model.CustomerDetailsDto;
import com.alok.accounts.repository.AccountsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountsServiceImpl implements AccountsService {

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private CardsFeignClient cardsFeignClient;

    @Autowired
    private LoansFeignClient loansFeignClient;

    @Autowired
    private AccountAdaptor accountAdaptor;


    @Override
    public Accounts getAccountDetails(int customerId) {
      return Optional.ofNullable(accountsRepository.findByCustomerId(customerId))
               .orElse(new Accounts());
    }

    @Override
    public CustomerDetailsDto customerDetails(Integer customerId, String correlationId) {
        Accounts accountDetails = getAccountDetails(customerId);
        List<LoansDto> loansDetails = loansFeignClient.getLoansDetails(customerId, correlationId);
        log.info("loansFeignClient response {}", loansDetails.size());
        List<CardsDto> cardDetails = cardsFeignClient.getCardDetails(customerId, correlationId);
        log.info("cardsFeignClient response {}", cardDetails.size());
       return CustomerDetailsDto.builder()
                .accounts(accountAdaptor.adapt(accountDetails))
                .cards(cardDetails)
                .loans(loansDetails).build();
    }

    @Override
    public CustomerDetailsDto getCustomerDetailsFallBack(Integer customerId,String correlationId) {
        Accounts accountDetails = getAccountDetails(customerId);
        List<LoansDto> loansDetails = loansFeignClient.getLoansDetails(customerId, correlationId);
        return CustomerDetailsDto.builder()
                .accounts(accountAdaptor.adapt(accountDetails))
                .loans(loansDetails).build();
    }
}
