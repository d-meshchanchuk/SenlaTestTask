package com.senla.bankApp.menu.actions.impl;

import com.senla.bankApp.menu.actions.Action;
import com.senla.bankApp.service.ATMService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CheckBalanceAction implements Action {

    private final ATMService atmService;

    @Override
    public void execute() {
        System.out.println("Balance: " + atmService.checkBalance());
    }
}
