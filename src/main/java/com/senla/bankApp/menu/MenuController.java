package com.senla.bankApp.menu;

import com.senla.bankApp.exception.AuthorizationException;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Order(2)
@Component
@AllArgsConstructor
public class MenuController implements CommandLineRunner {

    private final Builder builder;
    private final Navigator navigator;

    @Override
    public void run(String... args) {
        navigator.setCurrentMenu(builder.getStartMenu());
        navigator.printMenu();
        int index;
        int i = 0;
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                if (scanner.hasNext()) {
                    index = scanner.nextInt();
                    navigator.navigate(index);
                    navigator.printMenu();
                }
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                System.out.println("Некорректный ввод данных, или пункт меню не существует");
                navigator.printMenu();
            } catch (AuthorizationException e) {
                System.out.println(e.getMessage());
                navigator.printMenu();
            }
        }
    }
}
