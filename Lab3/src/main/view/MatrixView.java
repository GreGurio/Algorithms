package com.gmail.gurik.view;

import com.gmail.gurik.model.Matrix;

public class MatrixView {

    //Отображение матрицы в консоль при Matrix-аргументе
    static public void showMatrix(Matrix matrix) {
        for(int i = 0 ; i < matrix.getMatrix().length; i++) {
            System.out.print("[ ");

            for (int j = 0; j < matrix.getMatrix()[0].length; j++) {
                System.out.print(matrix.getMatrix()[i][j]);

                if (j != matrix.getMatrix()[0].length - 1)
                    System.out.print(", ");
            }
            System.out.println(" ]");
        }
    }

    //Отображение матрицы в консоль при массивном аргументе
    static public void showMatrix(int[][] matrix) {
        for(int i = 0 ; i < matrix.length; i++) {
            System.out.print("[ ");

            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]);

                if (j != matrix[0].length - 1)
                    System.out.print(", ");
            }
            System.out.println(" ]");
        }
    }

    static public boolean checkMatrix(String matrix) {
        //Разделение строки на массив чисел
        String[] nums = matrix.trim().split(" ");

        try {
            //Проверка того, что матрица квадратная
            if (Math.sqrt(nums.length) % 1 != 0)
                return false;

            //Проверка правильности заданых типов
            for (String num : nums) {

                try {
                    Integer.parseInt(num);
                } catch (Exception e) {
                    return false;
                }

            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    static public boolean checkMatrix(int[][] matrix) {
        //Проверка того, что матрица квадратная
        if (matrix.length != matrix[0].length)
            return false;
        return true;
    }

    static public boolean checkMatrix(Matrix object) {
        int[][] matrix = object.getMatrix();
        //Проверка того, что матрица квадратная
        if (matrix.length != matrix[0].length)
            return false;
        return true;
    }

    static public Matrix LineToMatrix(String line) {
        //Разделение одной линии на массив
        String[] nums = line.trim().split(" ");

        //Проверка того, что матрица квадратная
        if (Math.sqrt(nums.length) % 1 != 0)
            throw new IllegalArgumentException("Неверный ввод, матрица должна быть квадратной.");

        //Размер матрицы и создание возвращаемой матрицы
        int size = (int)Math.sqrt(nums.length);
        int[][] matrix = new int[size][size];

        //Заполнение возвращаемого массива(матрицы)
        int c = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = Integer.parseInt(nums[c]);
                c++;
            }
        }
        return new Matrix(matrix);
    }

    public void someFunc() {

    }
}
