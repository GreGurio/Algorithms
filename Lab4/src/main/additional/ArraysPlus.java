package com.gmail.gurik.additional;

/**
 * Клас, що містить допоміжні методи у роботі з масивами. Наприклад: виведення масивів на екран, приведення типів
 * масивів, перевірка чи всі елементи масиву цілі, копіювання масиву.
 * @author - Гуріненко Андрій, група ТІ-91
 */

public class ArraysPlus {

    /**
     * Метод для виведення на екран одновимірного масиву цілих чисел.
     * @param array - Масив, який виводиться на екран.
     */
    public static void show(int[] array) {
        System.out.print("[ ");

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);

            if(i != array.length - 1)
                System.out.print(",");

            System.out.print(" ");
        }
        System.out.println("]");
    }

    /**
     * Метод для виведення на екран одновимірного масиву чисел з плаваючою точкою.
     * @param array - Масив, який виводиться на екран.
     */
    public static void show(double[] array) {
        System.out.print("[ ");

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);

            if(i != array.length - 1)
                System.out.print(",");

            System.out.print(" ");
        }
        System.out.println("]");
    }

    /**
     * Метод для виведення на екран двовимірного масиву цілих чисел.
     * @param array - Масив, який виводиться на екран.
     */
    public static void show(int[][] array) {

        for(int i = 0; i < array.length; i++) {
            System.out.print("[ ");

            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);

                if (j != array[i].length - 1)
                    System.out.print(",");

                System.out.print(" ");
            }
            System.out.println("]");
        }
    }

    /**
     * Метод для виведення на екран двовимірного масиву чисел з плаваючою точкою.
     * @param array - Масив, який виводиться на екран.
     */
    public static void show(double[][] array) {

        for(int i = 0; i < array.length; i++) {
            System.out.print("[ ");

            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);

                if (j != array[i].length - 1)
                    System.out.print(",");

                System.out.print(" ");
            }
            System.out.println("]");
        }
    }



    /**
     * Метод для переведення строки, яка складається з чисел у одновимірний масив цілих чисел.
     * @param line - Строка, яка буде розшифровуватись у масив.
     * @return - Повертає одновимірний масив цілих чисел.
     * @throws NumberFormatException - Неправильний тип числа. (Не ціле число)
     */
    public static int[] lineToIntArray(String line) throws NumberFormatException {
        String[] nums = line.trim().split(" ");
        int[] array = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            array[i] = Integer.parseInt(nums[i]);
        }
        return array;
    }

    /**
     * Метод для переведення строки, яка складається з чисел у одновимірний масив чисел з плаваючою точкою.
     * @param line - Строка, яка буде розшифровуватись у масив.
     * @return - Повертає одновимірний масив чисел з плаваючою точкою.
     * @throws NumberFormatException - Неправильний тип числа. (Не число з плаваючою точкою)
     */
    public static double[] lineToDoubleArray(String line) throws  NumberFormatException {
        String[] nums = line.trim().split(" ");
        double[] array = new double[nums.length];

        for (int i = 0; i < nums.length; i++) {
            array[i] = Double.parseDouble(nums[i]);
        }
        return array;
    }

    /**
     * Метод для переведення строки, яка складається з чисел у двовимірний масив цілих чисел.
     * @param line - Строка, яка буде розшифровуватись.
     * @param isTwoDimension - Прапорець, що очікуваний масив має бути двовимірним.
     * @return - Повертає двовимірний масив цілих чисел.
     * @throws NumberFormatException - Неправильний тип числа. (Не ціле число)
     * @throws RuntimeException - Помилка кількості чисел. Масив має бути квадратним.
     */
    public static int[][] lineToIntArray(String line, boolean isTwoDimension) throws NumberFormatException, RuntimeException {
        if(!isTwoDimension)
            throw new RuntimeException();

        String[] nums = line.trim().split(" ");

        if (Math.sqrt(nums.length) % 1 != 0)
            throw new RuntimeException("Неправильна кільксть чисел. Масив мажє бути квадратним.");

        int size = (int)Math.sqrt(nums.length);
        int[][] array = new int[size][size];
        int c = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j ++) {
                array[i][j] = Integer.parseInt(nums[c]);
                c++;
            }
        }
        return array;
    }

    /**
     * Метод для переведення строки, яка складається з чисел у двовимірний масив чисел з плаваючою точкою.
     * @param line - Строка, яка буде розшифровуватись.
     * @param isTwoDimension - Прапорець, що очікуваний масив має бути двовимірним.
     * @return - Повертає двовимірний масив чисел з плаваючою точкою.
     * @throws NumberFormatException - Неправильний тип числа. (Не ціле число)
     * @throws RuntimeException - Помилка кількості чисел. Масив має бути квадратним.
     */
    public static double[][] lineToDoubleArray(String line, boolean isTwoDimension)
            throws NumberFormatException, RuntimeException {
        if(!isTwoDimension)
            throw new RuntimeException();

        String[] nums = line.trim().split(" ");

        if (Math.sqrt(nums.length) % 1 != 0)
            throw new RuntimeException("Неправильна кільксть чисел. Масив має бути квадратним.");

        int size = (int)Math.sqrt(nums.length);
        double[][] array = new double[size][size];
        int c = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j ++) {
                array[i][j] = Integer.parseInt(nums[c]);
                c++;
            }
        }
        return array;
    }



    /**
     * Метод для перевірки, чи всі елементи одновимірного массиву типу "з плаваючою точкою" не мають залишку,
     * тобто є цілими.
     * @param array - Массив, що буде перевірятись.
     * @return - Повертає true, якщо всі елементи масиву цілі, або false, якщо ні.
     */
    public static boolean isArrayInteger(double[] array) {
        for (double v : array) {
            if (v % 1 != 0)
                return false;
        }
        return true;
    }

    /**
     * Метод для перевірки, чи всі елементи двовимірного массиву типу "з плаваючою точкою" не мають залишку,
     * тобто є цілими.
     * @param array - Массив, що буде перевірятись.
     * @return - Повертає true, якщо всі елементи масиву цілі, або false, якщо ні.
     */
    public static boolean isArrayInteger(double[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] % 1 != 0)
                    return false;
            }
        }
        return true;
    }



    /**
     * Метод для створення копії одновимірного масиву цілих чисел.
     * @param array - Массив, що буде копіюватись.
     * @return - Одновимірний масив цілих чисел - копія параметру.
     */
    public static int[] copyOf(int[] array) {
        int[] copy = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }
        return copy;
    }

    /**
     * Метод для створення копії двовимірного масиву цілих чисел.
     * @param array - Массив, що буде копіюватись.
     * @return - Двовимірний масив цілих чисел - копія параметру.
     */
    public static int[][] copyOf(int[][] array) {
        int[][] copy = new int[array.length][array[0].length];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++)
                copy[i][j] = array[i][j];
        }
        return copy;
    }

    /**
     * Метод для створення копії одновимірного масиву чисел з плаваючою точкою.
     * @param array - Массив, що буде копіюватись.
     * @return - Одновимірний масив чисел з плаваючою точкою - копія параметру.
     */
    public static double[] copyOf(double[] array) {
        double[] copy = new double[array.length];

        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }
        return copy;
    }

    /**
     * Метод для створення копії двовимірного масиву чисел з плаваючою точкою.
     * @param array - Массив, що буде копіюватись.
     * @return - Двовимірний масив чисел з плаваючою точкою - копія параметру.
     */
    public static double[][] copyOf(double[][] array) {
        double[][] copy = new double[array.length][array[0].length];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++)
                copy[i][j] = array[i][j];
        }
        return copy;
    }

    /**
     * Метод для створення копії одновимірного масиву символів.
     * @param array - Массив, що буде копіюватись.
     * @return - Одновимірний масив символів - копія параметру.
     */
    public static char[] copyOf(char[] array) {
        char[] copy = new char[array.length];

        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }
        return copy;
    }

    /**
     * Метод для створення копії двовимірного масиву символів.
     * @param array - Массив, що буде копіюватись.
     * @return - Двовимірний масив символів - копія параметру.
     */
    public static char[][] copyOf(char[][] array) {
        char[][] copy = new char[array.length][array[0].length];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++)
                copy[i][j] = array[i][j];
        }
        return copy;
    }

    /**
     * Метод для створення копії одновимірного масиву строк.
     * @param array - Массив, що буде копіюватись.
     * @return - Одновимірний масив строк - копія параметру.
     */
    public static String[] copyOf(String[] array) {
        String[] copy = new String[array.length];

        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }
        return copy;
    }

    /**
     * Метод для створення копії двовимірного масиву строк.
     * @param array - Массив, що буде копіюватись.
     * @return - Двовимірний масив строк - копія параметру.
     */
    public static String[][] copyOf(String[][] array) {
        String[][] copy = new String[array.length][array[0].length];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++)
                copy[i][j] = array[i][j];
        }
        return copy;
    }


    /**
     * Метод для приведення одновимірного масиву цілих чисел до масиву чисел з плаваючою точкою.
     * @param array - Масив, що буде приводитись.
     * @return - Одновимірний масив чисел з плаваючою точкою.
     */
    public static double[] intToDouble(int[] array) {
        double[] doubleArray = new double[array.length];

        for (int i = 0; i < array.length; i++) {
            doubleArray[i] = array[i];
        }
        return doubleArray;
    }

    /**
     * Метод для приведення двовимірного масиву цілих чисел до масиву чисел з плаваючою точкою.
     * @param array - Масив, що буде приводитись.
     * @return - Двовимірний масив чисел з плаваючою точкою.
     */
    public static double[][] intToDouble(int[][] array) {
        double[][] doubleArray = new double[array.length][array[0].length];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++)
                doubleArray[i][j] = array[i][j];
        }
        return doubleArray;
    }

    /**
     * Метод для приведення одновимірного масиву чисел з плаваючою точкою до масиву цілих чисел.
     * @param array - Масив, що буде приводитись.
     * @return - Одновимірний масив цілих чисел.
     */
    public static int[] doubleToInt(double[] array) {
        int[] IntArray = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            IntArray[i] = (int)array[i];
        }
        return IntArray;
    }

    /**
     * Метод для приведення двовимірного масиву чисел з плаваючою точкою до масиву цілих чисел.
     * @param array - Масив, що буде приводитись.
     * @return - Двовимірний масив цілих чисел.
     */
    public static int[][] doubleToInt(double[][] array) {
        int[][] IntArray = new int[array.length][array[0].length];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++)
                IntArray[i][j] = (int)array[i][j];
        }
        return IntArray;
    }

}
