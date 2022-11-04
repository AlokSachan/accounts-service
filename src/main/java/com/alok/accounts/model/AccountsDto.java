package com.alok.accounts.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AccountsDto {

    private int customerId;
    private long accountNumber;
    private String accountType;
    private String branchAddress;
    private LocalDate createDt;
}
