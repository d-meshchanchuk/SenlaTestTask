package com.senla.bankApp.service;

import com.senla.bankApp.entity.Card;

import java.math.BigDecimal;

public interface ATMService {

    Boolean authorization(String number, String password);

    Boolean getMoney(Integer sum) throws Exception;

    Boolean putMoney (Integer sum) throws Exception;

    Boolean getAuthorization();

    void setAuthorization(Boolean authorization);
}
