package com.senla.bankApp.menu;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
@AllArgsConstructor
public class MenuController implements CommandLineRunner {

    private final Builder builder;
    private final Navigator navigator;

    @Override
    public void run(String... args) throws Exception {
        navigator.setCurrentMenu(builder.getRootMenu());
        navigator.printMenu();
        Integer index;
        int i = 0;
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                if (scanner.hasNext()) {
                    index = scanner.nextInt();
                    if (index.equals(-1)) {
                        return;
                    }
                    navigator.navigate(index);
                    navigator.printMenu();
                }
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                System.out.println("Некорректный ввод данных, или пункт меню не существует");
                navigator.printMenu();
            }
        }
    }
}
