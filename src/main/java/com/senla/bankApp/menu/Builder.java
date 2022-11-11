package com.senla.bankApp.menu;

import com.senla.bankApp.menu.actions.impl.ExitAction;
import org.springframework.stereotype.Component;

@Component
public class Builder {

    private final Menu rootMenu = new Menu("Root menu");
    private final Menu getMoneyMenu = new Menu("Get money menu");
    private final Menu putMoneyMenu = new Menu("Put money menu");


    public Menu getRootMenu() {
        this.buildMenu();
        return rootMenu;
    }

    private void buildMenu() {
        rootMenu.addMenuItem(
                new MenuItem("0 - Exit",
                        new ExitAction(),
                        rootMenu));
        rootMenu.addMenuItem(
                new MenuItem("1 - Get money",
                        () -> System.out.println("Go to Master menu"),
                        buildGetMoneyMenu()));
        rootMenu.addMenuItem(
                new MenuItem("2 - Put money",
                        () -> System.out.println("Go to Order menu"),
                        buildPutMoneyMenu()));
    }

    private Menu buildGetMoneyMenu() {
        getMoneyMenu.addMenuItem(new MenuItem("0 - root menu",
                () -> System.out.println("Go to root menu"),
                rootMenu));
        getMoneyMenu.addMenuItem(
                new MenuItem("1 - Get money",
                        () -> System.out.println("Go to root menu"),
                        rootMenu));
        return getMoneyMenu;
    }

    private Menu buildPutMoneyMenu() {
        putMoneyMenu.addMenuItem(new MenuItem("0 - root menu",
                () -> System.out.println("Go to root menu"),
                rootMenu));
        putMoneyMenu.addMenuItem(
                new MenuItem("1 - Put money",
                        () -> System.out.println("Go to root menu"),
                        rootMenu));
        return putMoneyMenu;
    }




}
