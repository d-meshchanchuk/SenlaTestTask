package com.senla.bankApp.service.impl;

import com.senla.bankApp.entity.Card;
import com.senla.bankApp.exception.AuthorizationException;
import com.senla.bankApp.exception.LimitException;
import com.senla.bankApp.exception.NoEnoughMoneyException;
import com.senla.bankApp.repository.impl.ATMRepositoryImpl;
import com.senla.bankApp.service.ATMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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
    public void authorization(String number, String password) {

        int countFailAuthorization;

        Optional<Card> cardOptional = atmRepository.getCard(number);

        if (cardOptional.isPresent()) {

            card = cardOptional.get();
            countFailAuthorization = card.getCountBlock();

            if (LocalDateTime.now().isBefore(card.getTimeBlock())) {
                throw new AuthorizationException("Card blocked");
            } else if (password.equals(card.getPassword())) {
                isAuthorization = true;
                countFailAuthorization = 0;
                card.setCountBlock(countFailAuthorization);
                atmRepository.saveCard(card);
            } else if (countFailAuthorization == 2) {
                card.setCountBlock(0);
                card.setTimeBlock(LocalDateTime.now().plusHours(24));
                atmRepository.saveCard(card);
                throw new AuthorizationException("Card blocked");
            } else {
                countFailAuthorization++;
                card.setCountBlock(countFailAuthorization);
                atmRepository.saveCard(card);
                throw new AuthorizationException("Invalid password," + countFailAuthorization + " attempts out of 3!");
            }
        } else {
            throw new AuthorizationException("Card not found");
        }
    }

    @Override
    public void getMoney(Integer sum) {
        if (isAuthorization) {
            if (sum > bankBalance) {
                throw new NoEnoughMoneyException("Not enough money in the ATM");
            } else if (sum > card.getBalance()) {
                throw new NoEnoughMoneyException("Not enough money in the account");
            } else {
                card.setBalance(card.getBalance() - sum);
                bankBalance -= sum;
                atmRepository.saveCard(card);
            }
        }
    }

    @Override
    public void putMoney(Integer sum) {
        if (isAuthorization) {
            if (sum < topUpLimit) {
                card.setBalance(card.getBalance() + sum);
                bankBalance += sum;
                atmRepository.saveCard(card);
            } else {
                throw new LimitException("Top-up limit exceeded(Current limit: " + topUpLimit + ")");
            }
        }
    }
}
