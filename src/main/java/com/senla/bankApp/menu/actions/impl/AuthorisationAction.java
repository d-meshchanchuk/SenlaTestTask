package com.senla.bankApp.menu.actions.impl;

import com.senla.bankApp.menu.actions.Action;
import com.senla.bankApp.service.ATMService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@AllArgsConstructor
public class AuthorisationAction implements Action {

    private final ATMService atmService;

    @Override
    public void execute() {

            Scanner scanner = new Scanner(System.in);

            System.out.println("Card number:");
            String number = scanner.nextLine();

            System.out.println("Password:");
            String password = scanner.nextLine();

            atmService.authorization(number, password);
    }
}
