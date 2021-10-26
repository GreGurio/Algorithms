package com.gmail.gurik.controller;

import com.gmail.gurik.Logger;
import com.gmail.gurik.command.*;
import com.gmail.gurik.model.Model;
import com.gmail.gurik.view.View;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import java.util.Scanner;

/**
 * Головний клас контроллер.
 * @author - Гуріненко Андрій, група ТІ-91.
 */

public class Controller {
    View view;
    Model model;



    //-------ПРИЙНЯТТЯ ВВОДУ ВІД КОРИСТУВАЧА---------

    /**
     * Звичайне прийняття вводу від користувача
     * @return - Повертає строку, яку ввів користувач.
     */
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Прийняття вводу від користувача з попереднім виведенням йому повідомленням.
     * @param message - Повідомлення, що буде виведено користувачеві перед введенням.
     * @return - Повертає строку, яку ввів користувач.
     */
    public String getInput(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }



    //----------ВЗАЄМОДІЯ З КОРИСТУВАЧЕМ-----------

    /**
     * Метод, що циклічно приймає команди від користувача та передає в валідатор команд для виконання необхідних дій,
     * поки користувач не зупинить виконання.
     * @param message - Повідомлення, що буде виводитись кожен раз, коли програма очікує команду користувача.
     */
    public void userInteraction(String message) {
        String input;
        do {
            input = this.getInput(message);
            Logger.getLogger().add("- [INPUT] - " + input);
            validateCommand(input);
        } while(!input.equals("/exit"));
    }



    //--------------ВАЛІДАЦІЯ КОМАНД--------------

    /**
     * Метод, що оброблює введені користувачем команди та викликає методи, що виконують відповідні дії.
     * @param line - Введена користувачем команда.
     */
    public void validateCommand(String line) {

        Command command;

        //Довідка
        if (line.equals("/help")) {
            command = new HelpCommand(this.getView());
        }
        //Вихід
        else if (line.equals("/exit")) {
            command = new StopCommand();
        }
        //Робота з матрицею
        else if (line.equals("/solve matrix")) {
            command = new SolveMatrixCommand(this.getModel());
        }
        //Робота з СЛАУ
        else if (line.equals("/solve slar")) {
            command = new SolveSlarCommand(this.getModel());
        }
        //Неправильне введення
        else {
            command = new WrongCommand(this.getView());
        }

        command.execute();

    }

    //Сеттери
    @Setter
    public void setView(View view) {
        this.view = view;
    }
    @Setter
    public void setModel(Model model) {
        this.model = model;
    }

    //Геттери
    @Getter
    public View getView() {
        return view;
    }
    @Getter
    public Model getModel() {
        return model;
    }

}
