package com.senla.bankApp.service;

import com.senla.bankApp.entity.Card;
import com.senla.bankApp.exception.AuthorizationException;
import com.senla.bankApp.exception.LimitException;
import com.senla.bankApp.exception.NoEnoughMoneyException;

import java.math.BigDecimal;

public interface ATMService {

    void authorization(String number, String password) throws AuthorizationException;

    void getMoney(Integer sum) throws NoEnoughMoneyException;

    void putMoney (Integer sum) throws LimitException;

}
