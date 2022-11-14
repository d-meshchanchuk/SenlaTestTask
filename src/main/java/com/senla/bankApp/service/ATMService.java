package com.senla.bankApp.service;

import com.senla.bankApp.exception.AuthorizationException;
import com.senla.bankApp.exception.LimitException;
import com.senla.bankApp.exception.NoEnoughMoneyException;

public interface ATMService {

    void authorization(String number, String password) throws AuthorizationException;

    Integer checkBalance ();

    void getMoney(Integer sum) throws NoEnoughMoneyException;

    void putMoney (Integer sum) throws LimitException;

}
