package com.gmail.gurik.view;

import com.gmail.gurik.additional.ArraysPlus;
import com.gmail.gurik.additional.CustomMessages;
import com.gmail.gurik.builder.Constructor;
import com.gmail.gurik.builder.MatrixBuilder;
import com.gmail.gurik.model.Matrix;
import com.gmail.gurik.model.Model;


public class MatrixView implements View {

    public void show(Model model) {
        Matrix obj = (Matrix) model;
        double[][] matrix = obj.getMatrix();
        double result = obj.getResult()[0];
        boolean isMatrixInteger = ArraysPlus.isArrayInteger(matrix);
        boolean isResultInteger = (result % 1 == 0);

        System.out.println("---------------------------------");
        System.out.println("MATRIX: ");

        for(int i = 0 ; i < matrix.length; i++) {
            System.out.print("[ ");

            for (int j = 0; j < matrix[0].length; j++) {
                if (isMatrixInteger)
                    System.out.print((int)matrix[i][j]);
                else
                    System.out.print(matrix[i][j]);

                if (j != matrix[0].length - 1)
                    System.out.print(", ");
            }
            System.out.println(" ]");
        }
        System.out.println("---------------------------------");
        System.out.print("RESULT: ");
        if (isResultInteger)
            System.out.println((int)result + ";");
        else
            System.out.println(result + ";");
        System.out.println();

    }

    public Matrix LineTo(String line) {
        //Разделение одной линии на массив
        String[] nums = line.trim().split(" ");

        //Проверка того, что матрица квадратная
        if (Math.sqrt(nums.length) % 1 != 0)
            throw new IllegalArgumentException("Неверный ввод, матрица должна быть квадратной.");

        //Размер матрицы и создание возвращаемой матрицы
        int size = (int)Math.sqrt(nums.length);
        double[][] matrix = new double[size][size];

        //Заполнение возвращаемого массива(матрицы)
        int c = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                try {
                    matrix[i][j] = Integer.parseInt(nums[c]);
                } catch (NumberFormatException e) {
                    matrix[i][j] = Double.parseDouble(nums[c]);
                }

                c++;
            }
        }
        Constructor constructor = new Constructor();
        constructor.setBuilder(new MatrixBuilder());
        return (Matrix)constructor.getModel(matrix);
    }

    public void help() {
        CustomMessages.yellow("ИНФОРМАЦИЯ");
        CustomMessages.yellow("-----------------------------");
        System.out.println("Вы должны ввести все числа в матрице через пробел. Матрица должна " +
                "быть квадратной." + "\n");
        System.out.println("Пример:");
        System.out.println("Матрица 3x3" + "\n");
        System.out.println("[1,2,3]");
        System.out.println("[4,5,6]");
        System.out.println("[7,8,9]" + " \n");
        System.out.println("Нужно ввести: \"1 2 3 4 5 6 7 8 9\"");
        CustomMessages.yellow("-----------------------------" + "\n\n");
    }

    public void greeting() {
        System.out.println("---------------------------------------");
        System.out.println("Matrix Controller");
        System.out.println("---------------------------------------");
    }
     public void finishing() {
         System.out.println("Work with Matrix finished.");
     }

     public void wrong() {
        CustomMessages.red("Не правильне введення. Матриця має бути квадратною. /help");
     }
}
