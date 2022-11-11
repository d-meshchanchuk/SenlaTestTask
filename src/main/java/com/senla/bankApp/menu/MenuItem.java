package com.senla.bankApp.menu;

import com.senla.bankApp.menu.actions.Action;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MenuItem {
    private String title;
    private Action action;
    private Menu nextMenu;

    public MenuItem(String title, Action action, Menu nextMenu) {
        this.title = title;
        this.action = action;
        this.nextMenu = nextMenu;
    }

    public void doAction() {
        action.execute();
    }

}
