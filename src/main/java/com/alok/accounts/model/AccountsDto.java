package com.alok.accounts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)

public class AccountsDto {
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int customerId;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private long accountNumber;
    private String accountType;
    private String branchAddress;
    private LocalDate createDt;
}
