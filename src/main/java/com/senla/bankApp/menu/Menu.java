package com.senla.bankApp.menu;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Menu {
    private String name;
    private List<MenuItem> menuItem = new ArrayList<>();

    public Menu(String name) {
        this.name = name;
    }

    public void addMenuItem(MenuItem item) {
        menuItem.add(item);
    }

}
