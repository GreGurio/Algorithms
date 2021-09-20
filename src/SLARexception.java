package com.gmail.kpi;


//Класс исключения для ошибки задания СЛАУ
public class SLARexception extends Exception {
    String message;

    public SLARexception() {
        this.message = "Underfined SLAR exception.";
    }
    public SLARexception(String message) {
        this.message = message;
    }
}
