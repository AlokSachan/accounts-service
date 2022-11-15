package com.alok.accounts.service;

import com.alok.accounts.adaptor.AccountAdaptor;
import com.alok.accounts.entity.Accounts;
import com.alok.accounts.feign.client.CardsFeignClient;
import com.alok.accounts.feign.client.LoansFeignClient;
import com.alok.accounts.feign.response.CardsDto;
import com.alok.accounts.feign.response.LoansDto;
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
    public CustomerDetailsDto customerDetails(Integer customerId) {
        Accounts accountDetails = getAccountDetails(customerId);
        List<CardsDto> cardDetails = cardsFeignClient.getCardDetails(customerId);
        log.info("cardsFeignClient response {}", cardDetails.size());
        List<LoansDto> loansDetails = loansFeignClient.getLoansDetails(customerId);
        log.info("loansFeignClient response {}", loansDetails.size());
       return CustomerDetailsDto.builder()
                .accounts(accountAdaptor.adapt(accountDetails))
                .cards(cardDetails)
                .loans(loansDetails).build();
    }
}
