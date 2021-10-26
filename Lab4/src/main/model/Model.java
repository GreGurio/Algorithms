package com.gmail.gurik.model;

/**
 * Интерфейс стандартної математичної моделі.
 */
public interface Model {
    void solve();           //Метод для вирішення моделі
    double[] getResult();   //Метод для повернення масиву резльтатів рішення моделі
}
