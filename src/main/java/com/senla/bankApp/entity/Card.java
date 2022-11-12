package com.senla.bankApp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private String number;
    private String password;
    private Integer balance;
    private String block;
}
