package com.gmail.gurik.services;

import com.gmail.gurik.sources.Messages;

public class ShowService {

    static public void showSLARInfo() {
        Messages.warn("ИНФОРМАЦИЯ");
        Messages.warn("-----------------------------");
        System.out.println("Вы должны ввести все числа СЛАУ по очереди через пробел. " +
                "Матрица СЛАУ должна быть квадратной. Элементы стобца свободных" +
                " членов должны вводить после элементов матрицы.");
        System.out.println("Пример:");
        System.out.println("СЛАУ 3x3" + "\n");
        System.out.println("[1,2,3 | 12]");
        System.out.println("[4,5,6 | 10]");
        System.out.println("[7,8,9 | 11]" + " \n");
        System.out.println("Нужно ввести: \"1 2 3 4 5 6 7 8 9 12 10 11\"");
        Messages.warn("-----------------------------" + "\n\n");
    }

    static public void showMatrixInfo() {
        Messages.warn("ИНФОРМАЦИЯ");
        Messages.warn("-----------------------------");
        System.out.println("Вы должны ввести все числа в матрице через пробел. Матрица должна " +
                "быть квадратной." + "\n");
        System.out.println("Пример:");
        System.out.println("Матрица 3x3" + "\n");
        System.out.println("[1,2,3]");
        System.out.println("[4,5,6]");
        System.out.println("[7,8,9]" + " \n");
        System.out.println("Нужно ввести: \"1 2 3 4 5 6 7 8 9\"");
        Messages.warn("-----------------------------" + "\n\n");
    }

    static public void showHelp() {
        Messages.warn("-----------------------------------------------------------------------");
        Messages.warn("Help:");
        System.out.println("\t" + "/solve matrix - Matrix Calculator. You need to enter a Matrix.");
        System.out.println("\t" + "/solve slar - SLAR Calculator. You need to enter a SLAR.");
        System.out.println("\t" + "/exit - Finishing the program.");
        Messages.warn("-----------------------------------------------------------------------");
        System.out.println("\n\n");
    }
}
