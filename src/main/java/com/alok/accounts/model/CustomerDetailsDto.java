package com.alok.accounts.model;

import com.alok.accounts.feign.response.CardsDto;
import com.alok.accounts.feign.response.LoansDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerDetailsDto {

    private AccountsDto accounts;
    private List<LoansDto> loans;
    private List<CardsDto> cards;
}
