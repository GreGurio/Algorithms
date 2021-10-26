package com.gmail.gurik.controller;

import com.gmail.gurik.command.*;
import com.gmail.gurik.model.Slar;

/**
 * Клас контроллер СЛАУ.
 * @author - Гуріненко Андрій, група ТІ-91.
 */

public class SlarController extends Controller {

    /**
     * Метод, що оброблює введені користувачем команди та викликає методи, що виконують відповідні дії.
     * @param line - Введена користувачем команда.
     */
    @Override
    public void validateCommand(String line) {

        boolean correct;    //Прапорець про корректність СЛАУ

        //Перевірка корректності СЛАУ, якщо вона введена
        try {
            correct = Slar.isCorrect(line);
        } catch (Exception e) {
            correct = false;
        }

        Command command;

        //Довідка
        if (line.equals("/help")) {
            command = new HelpCommand(this.getView());
        }
        //Вихід
        else if (line.equals("/exit")) {
            command = new StopCommand();
        }
        //Оперіції з СЛАУ
        else if (correct) {
            this.model = view.LineTo(line);
            this.getView().show(this.model);
            command = new StopCommand();
        }
        //Неправильне введення
        else {
            command = new WrongCommand(this.getView());
        }
        command.execute();
    }

}
