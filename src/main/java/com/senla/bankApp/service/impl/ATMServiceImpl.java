package com.senla.bankApp.service.impl;

import com.senla.bankApp.entity.Card;
import com.senla.bankApp.repository.impl.ATMRepositoryImpl;
import com.senla.bankApp.service.ATMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ATMServiceImpl implements ATMService, CommandLineRunner {

    @Value("${top.up.limit}")
    private Integer topUpLimit;
    @Value("${bank.balance}")
    private Integer bankBalance;
    @Autowired
    private ATMRepositoryImpl atmRepository;


    @Override
    public Card authorization(String number, String password) throws Exception {
        Optional<Card> cardOptional = atmRepository.getCard(number, password);
        return cardOptional.orElseThrow(Exception::new);
    }

    @Override
    public Boolean getMoney(Card card, Integer sum) throws Exception {
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
    public Boolean putMoney(Card card, Integer sum) throws Exception {
        if (sum < topUpLimit) {
            card.setBalance(card.getBalance() + sum);
            bankBalance += sum;
            atmRepository.saveCard(card);
            return true;
        } else {
            throw new Exception();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(topUpLimit);
    }
}
