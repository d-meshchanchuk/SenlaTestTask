package com.senla.bankApp.menu;

import com.senla.bankApp.menu.actions.impl.AuthorisationAction;
import com.senla.bankApp.menu.actions.impl.ExitAction;
import com.senla.bankApp.menu.actions.impl.GetMoneyAction;
import com.senla.bankApp.menu.actions.impl.PutMoneyAction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Builder {

    private final ExitAction exitAction;
    private final GetMoneyAction getMoneyAction;
    private final PutMoneyAction putMoneyAction;
    private AuthorisationAction authorisationAction;
    private final Menu mainMenu = new Menu("Main menu");
    private final Menu authorisationMenu = new Menu("Authorisation menu");


    public Menu getStartMenu() {
        this.buildMenu();
        return authorisationMenu;
    }

    private void buildMenu() {
        authorisationMenu.addMenuItem(
                new MenuItem("0 - Exit",
                        exitAction,
                        authorisationMenu));
        authorisationMenu.addMenuItem(
                new MenuItem("1 - Insert card",
                        authorisationAction,
                        buildMainMenu()));
    }

    private Menu buildMainMenu() {
        mainMenu.addMenuItem(
                new MenuItem("0 - Return card",
                        exitAction,
                        authorisationMenu));
        mainMenu.addMenuItem(
                new MenuItem("1 - Get money",
                        getMoneyAction,
                        mainMenu));
        mainMenu.addMenuItem(
                new MenuItem("2 - Put money",
                        putMoneyAction,
                        mainMenu));
        return mainMenu;
    }
}
