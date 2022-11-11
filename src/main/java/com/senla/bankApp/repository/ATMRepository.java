package com.senla.bankApp.repository;

import com.senla.bankApp.entity.Card;

import java.util.Optional;

public interface ATMRepository {

    Optional<Card> getCard(String number, String password);

    void saveCard(Card card);
}
