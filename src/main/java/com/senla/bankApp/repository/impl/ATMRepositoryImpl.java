package com.senla.bankApp.repository.impl;

import com.senla.bankApp.entity.Card;
import com.senla.bankApp.repository.ATMRepository;
import com.senla.bankApp.utils.FileManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ATMRepositoryImpl implements ATMRepository, CommandLineRunner {

    @Autowired
    private final FileManager fileManager;
    private Map<String, String> storage;

    @Override
    public void run(String... args) throws Exception {
        storage = fileManager.readFile();
//        Card card = getCard("2222-1111-1111-1111", "1234");
//        System.out.println(card);
//        card.setPassword("1111");
//        saveCard(card);
//        storage.forEach((s, s2) -> System.out.println(s + " " + s2));
    }

    @Override
    public Optional<Card> getCard(String number, String password) {

        Optional<Card> card = Optional.empty();


        if (storage.containsKey(number)) {
            String[] info = storage.get(number).split(" ");
            card = Optional.of(Card.builder()
                    .number(number)
                    .password(info[0])
                    .balance(Integer.valueOf(info[1]))
                    .block(info[2])
                    .build());
        }

        return card;
    }

    @Override
    public void saveCard(Card card) {
        storage.put(card.getNumber(), card.getPassword() + " " + String.valueOf(card.getBalance()) + " " + card.getBlock());
    }
}
