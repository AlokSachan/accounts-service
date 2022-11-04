package com.alok.accounts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDto {

    private int customerId;
    private String name;
    private String email;
    private String mobileNumber;
    private LocalDate createDt;
}
