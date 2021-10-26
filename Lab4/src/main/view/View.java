package com.gmail.gurik.view;

import com.gmail.gurik.model.Model;

/**
 * Інтерфейс для стандартної репрезентаї інтерфейсу користувачеві.
 * @author - Гуріненко Андрій, група ТІ-91
 */

public interface View {
    void show(Model model);     //Виведення моделі на екран
    void help();                //Виведення довідки на екран
    void greeting();            //Виведення стартового повідомлення
    void finishing();           //Виведення кінцевого повідомлення
    void wrong();               //Виведення повідомлення про неправильне введення
    Model LineTo(String line);  //Створення моделі на основі даних з введеної строки
}
