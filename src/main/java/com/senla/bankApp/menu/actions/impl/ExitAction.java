package com.senla.bankApp.menu.actions.impl;

import com.senla.bankApp.menu.actions.Action;
import org.springframework.stereotype.Component;

@Component
public class ExitAction implements Action {
    @Override
    public void execute() {
        System.exit(0);
    }
}
