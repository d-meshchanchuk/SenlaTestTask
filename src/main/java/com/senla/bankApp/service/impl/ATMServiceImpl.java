package com.senla.bankApp.service.impl;

import com.senla.bankApp.entity.Card;
import com.senla.bankApp.exception.LimitException;
import com.senla.bankApp.exception.NoEnoughMoneyException;
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
    public void authorization(String number, String password) {

        Optional<Card> cardOptional = atmRepository.getCard(number, password);

        if (cardOptional.isPresent()) {
            isAuthorization = true;
            card = cardOptional.get();
        } else {
            throw new ArithmeticException();
        }
    }

    @Override
    public void getMoney(Integer sum) {
        if (isAuthorization) {
            if (sum > bankBalance) {
                throw new NoEnoughMoneyException("Недостаточно средств в банкомате");
            } else if (sum > card.getBalance()) {
                throw new NoEnoughMoneyException("Недостаточно средств на счете");
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
                throw new LimitException("Превышение лимита на пополнение(Текущий лимит: " + topUpLimit + ")");
            }
        }
    }
}
