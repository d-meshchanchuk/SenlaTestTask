package com.senla.bankApp.repository.impl;

import com.senla.bankApp.entity.Card;
import com.senla.bankApp.repository.ATMRepository;
import com.senla.bankApp.utils.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Component
public class ATMRepositoryImpl implements ATMRepository {

    @Autowired
    private final FileManager fileManager;
    private final Map<String, String> storage;

    public ATMRepositoryImpl(FileManager fileManager) {
        this.fileManager = fileManager;
        storage = fileManager.readFile();
    }

    @Override
    public Optional<Card> getCard(String number) {

        Optional<Card> card = Optional.empty();

        if (storage.containsKey(number)) {
            String[] info = storage.get(number).split(" ");

            card = Optional.of(Card.builder()
                    .number(number)
                    .password(info[0])
                    .balance(Integer.valueOf(info[1]))
                    .countBlock(Integer.valueOf(info[2]))
                    .timeBlock("0".equals(info[3]) ? LocalDateTime.MIN : LocalDateTime.parse(info[3]))
                    .build());
        }
        return card;
    }

    @Override
    public void saveCard(Card card) {
        storage.put(card.getNumber(), card.getPassword() + " "
                + card.getBalance() + " "
                + card.getCountBlock() + " "
                + card.getTimeBlock());
        fileManager.writeInfo(storage);
    }
}
