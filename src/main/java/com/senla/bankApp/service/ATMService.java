package com.senla.bankApp.service;

import com.senla.bankApp.entity.Card;

import java.math.BigDecimal;

public interface ATMService {

    Card authorization(String number, String password) throws Exception;

    Boolean getMoney(Card card, Integer sum) throws Exception;

    Boolean putMoney (Card card, Integer sum) throws Exception;
}
