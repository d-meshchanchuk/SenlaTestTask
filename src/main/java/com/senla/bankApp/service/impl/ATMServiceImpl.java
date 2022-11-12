package com.senla.bankApp.service.impl;

import com.senla.bankApp.entity.Card;
import com.senla.bankApp.repository.impl.ATMRepositoryImpl;
import com.senla.bankApp.service.ATMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ATMServiceImpl implements ATMService {

    @Value("${top.up.limit}")
    private Integer topUpLimit;
    @Value("${bank.balance}")
    private Integer bankBalance;
    @Autowired
    private ATMRepositoryImpl atmRepository;

    private Card card = new Card();
    private Boolean isAuthorization = false;


    @Override
    public Boolean authorization(String number, String password) {

        Optional<Card> cardOptional = atmRepository.getCard(number, password);

        if (cardOptional.isPresent()) {
            isAuthorization = true;
            card = cardOptional.get();
        }

        return isAuthorization;
    }

    @Override
    public Boolean getMoney(Integer sum) throws Exception {
        if (sum < bankBalance) {
            card.setBalance(card.getBalance() - sum);
            bankBalance -= sum;
            atmRepository.saveCard(card);
            return true;
        } else {
            throw new Exception();
        }
    }

    @Override
    public Boolean putMoney(Integer sum) throws Exception {
        if (sum < topUpLimit) {
            card.setBalance(card.getBalance() + sum);
            bankBalance += sum;
            atmRepository.saveCard(card);
            return true;
        } else {
            throw new Exception();
        }
    }

    public Boolean getAuthorization() {
        return isAuthorization;
    }

    public void setAuthorization(Boolean authorization) {
        isAuthorization = authorization;
    }
}
