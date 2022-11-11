package com.senla.bankApp.menu;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Navigator {
    private Menu currentMenu;

    public void printMenu() {
        System.out.println("Menu : " + currentMenu.getName());
        currentMenu.getMenuItem().forEach(item -> System.out.println(item.getTitle()));
    }

    public void navigate(Integer index) {
        if (currentMenu != null) {
            // if (currentMenu.getMenuItem().size() > index) {
            MenuItem menuItem = currentMenu.getMenuItem().get(index);
            menuItem.doAction();
            currentMenu = menuItem.getNextMenu();
            // } else {
//                System.out.println("Пункт меню не существует");
//            }
        }
    }
}
