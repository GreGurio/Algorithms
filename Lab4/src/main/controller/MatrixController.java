package com.gmail.gurik.controller;

import com.gmail.gurik.command.*;
import com.gmail.gurik.model.Matrix;

/**
 * Клас контроллер матриці.
 * @author - Гуріненко Андрій, група ТІ-91.
 */

public class MatrixController extends Controller {

    /**
     * Метод, що оброблює введені користувачем команди та викликає методи, що виконують відповідні дії.
     * @param line - Введена користувачем команда.
     */
    @Override
    public void validateCommand(String line) {

        boolean correct;    // Прапорець, що матриця є корректною

        // Перевірка матриці а корректність, якщо вона введена
        try {
            correct = Matrix.isSquare(line);
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
        //Операції з матрицею
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
