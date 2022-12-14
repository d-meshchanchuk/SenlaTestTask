package com.senla.bankApp.menu.actions.impl;

import com.senla.bankApp.exception.NoEnoughMoneyException;
import com.senla.bankApp.menu.actions.Action;
import com.senla.bankApp.service.ATMService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@AllArgsConstructor
public class GetMoneyAction implements Action {

    private final ATMService atmService;

    @Override
    public void execute() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Amount of money:");
            Integer sum = scanner.nextInt();
            atmService.getMoney(sum);
        } catch (NoEnoughMoneyException e) {
            System.out.println(e.getMessage());
        }
    }
}
