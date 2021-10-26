package com.gmail.gurik.view;

import com.gmail.gurik.additional.ArraysPlus;
import com.gmail.gurik.additional.CustomMessages;
import com.gmail.gurik.builder.Constructor;
import com.gmail.gurik.builder.SlarBuilder;
import com.gmail.gurik.model.Model;
import com.gmail.gurik.model.Slar;


public class SlarView implements View {

    public void show(Model model) {
        Slar slar = (Slar) model;
        double[][] matrix = slar.getMatrix();
        double[] freeArgs = slar.getFreeArgs();
        double[] result = slar.getResult();
        boolean isSlarInteger = ArraysPlus.isArrayInteger(matrix) && ArraysPlus.isArrayInteger(freeArgs);

        System.out.println("---------------------------------");
        System.out.println("SLAR: ");

        for (int i = 0; i < matrix.length; i++) {
            System.out.print("[ ");

            for (int j = 0; j < matrix[0].length; j++) {
                if (isSlarInteger)
                    System.out.print((int)matrix[i][j]);
                else
                    System.out.print(matrix[i][j]);

                if (j != matrix[0].length - 1)
                    System.out.print(", ");
            }
            System.out.print(" | ");
            if (isSlarInteger)
                System.out.print((int)freeArgs[i]);
            else
                System.out.print(freeArgs[i]);
            System.out.println(" ]");
        }

        System.out.println("---------------------------------");
        System.out.println("RESULT: ");
        for (int i = 0; i < result.length; i++)
            System.out.println("X" + (i+1) + " = " + result[i] + " ;");
        System.out.println("---------------------------------");

    }

    public Model LineTo (String line) {
        //Разделение строки на массив чисел
        String[] nums = line.trim().split(" ");

        int size = (int)Math.sqrt(nums.length);

        //Проверка правильности ввода СЛАУ
        if (Math.sqrt(nums.length - size) % 1 != 0)
            throw new IllegalArgumentException("Неправильный ввод СЛАУ.");

        //Подготовка
        size = (int) (Math.sqrt(nums.length) - (Math.sqrt(nums.length) % 1));
        int[][] matrix = new int[size][size];
        int[] freeArgs = new int[size];

        //Заполнение массива матрицы и массива свободных членов
        int c = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = Integer.parseInt(nums[c]);
                c++;
            }
        }
        for (int i = 0; i < size; i++) {
            freeArgs[i] = Integer.parseInt(nums[c]);
            c++;
        }
        Constructor constructor = new Constructor();
        constructor.setBuilder(new SlarBuilder());
        Model model = constructor.getModel(matrix, freeArgs);
        return model;
    }

    public void help() {
        CustomMessages.yellow("ИНФОРМАЦИЯ");
        CustomMessages.yellow("-----------------------------");
        System.out.println("Вы должны ввести все числа СЛАУ по очереди через пробел. " +
                "Матрица СЛАУ должна быть квадратной. Элементы стобца свободных" +
                " членов должны вводить после элементов матрицы.");
        System.out.println("Пример:");
        System.out.println("СЛАУ 3x3" + "\n");
        System.out.println("[1,2,3 | 12]");
        System.out.println("[4,5,6 | 10]");
        System.out.println("[7,8,9 | 11]" + " \n");
        System.out.println("Нужно ввести: \"1 2 3 4 5 6 7 8 9 12 10 11\"");
        CustomMessages.yellow("-----------------------------" + "\n\n");
    }

    public void greeting() {
        System.out.println("---------------------------------------");
        System.out.println("SLAR Controller");
        System.out.println("---------------------------------------");
    }

    public void wrong() {
        CustomMessages.red("Неправильне введення. Спробуйте ще раз. /help");
    }

    public void finishing() {
        System.out.println("Work with SLAR finished.");
    }
}
