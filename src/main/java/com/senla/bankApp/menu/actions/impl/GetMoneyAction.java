package com.senla.bankApp.menu.actions.impl;

import com.senla.bankApp.entity.Card;
import com.senla.bankApp.menu.actions.Action;
import com.senla.bankApp.service.ATMService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
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
        } catch (InputMismatchException e) {
            System.out.println("Некорректный ввод данных");
        } catch (Exception e) {
            //todo
            e.printStackTrace();
        }
    }
}
