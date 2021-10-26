package com.gmail.gurik.additional;

/**
 * Клас для створення виводу на екран у різних кольорах.
 * @author - Гуріненко Андрій, група ТІ-91
 */

public class CustomMessages {

    //Инициализация цветов
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    //Функции вывода текста нужным цветом
    public static void black(String str) {
        System.out.println(ANSI_BLACK + str + ANSI_RESET);
    }
    public static void red(String str) {
        System.out.println(ANSI_RED + str + ANSI_RESET);
    }
    public static void green(String str) {
        System.out.println(ANSI_GREEN + str + ANSI_RESET);
    }
    public static void yellow(String str) {
        System.out.println(ANSI_YELLOW + str + ANSI_RESET);
    }
    public static void blue(String str) {
        System.out.println(ANSI_BLUE + str + ANSI_RESET);
    }
    public static void purple(String str) {
        System.out.println(ANSI_PURPLE + str + ANSI_RESET);
    }
    public static void cyan(String str) {
        System.out.println(ANSI_CYAN + str + ANSI_RESET);
    }
    public static void white(String str) {
        System.out.println(ANSI_WHITE + str + ANSI_RESET);
    }

    //Функции с пустыми параметрами
    public static void black() {}
    public static void red() {}
    public static void green() {}
    public static void yellow() {}
    public static void blue() {}
    public static void purple() {}
    public static void cyan() {}
    public static void white() {}
}
