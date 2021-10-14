package com.gmail.gurik.view;

import com.gmail.gurik.model.SLAR;

public class SLARview {

    //Отображение СЛАУ при SLAR-аргументе
    static public void showSLAR(SLAR slar) {
        for (int i = 0; i < slar.getMatrix().length; i++) {
            System.out.print("[ ");

            for (int j = 0; j < slar.getMatrix()[0].length; j++) {
                System.out.print(slar.getMatrix()[i][j]);

                if (j != slar.getMatrix()[0].length-1)
                    System.out.print(", ");
            }
            System.out.println(" | " + slar.getFreeArg()[i] + " ]");
        }
    }

    //Отображение СЛАУ при массивных аргументах
    static public void showSLAR(int[][] slar, int[] freeArgs) {
        for (int i = 0; i < slar.length; i++) {
            System.out.print("[ ");

            for (int j = 0; j < slar[0].length; j++) {
                System.out.print(slar[i][j]);

                if (j != slar[0].length-1)
                    System.out.print(", ");
            }
            System.out.println(" | " + freeArgs[i] + " ]");
        }
    }

    static public boolean checkSLAR (String line) {
        //Разделение строки
        String[] nums = line.trim().split(" ");

        try {

            int size = (int) (Math.sqrt(nums.length) - (Math.sqrt(nums.length) % 1)); //Предполагаемый размер СЛАУ
            int newLen = nums.length - size; // Длина без предполагаемого столбца свободных членов

            //Проверка правильности размера матрицы
            if (Math.sqrt(newLen) % 1 != 0)
                return false;

            //Проверка на правильность типов значений
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

    static public boolean checkSLAR (int[][] generalView) {

        //Проверка на то, что размерность общего вида отличаеться только на 1 строку/столбец
        if ( (generalView.length+1) != generalView[0].length && generalView.length != (generalView[0].length+1))
            return false;

        return true;
    }

    static public boolean checkSLAR (int[][] matrix, int[] freeArgs) {
        //Проверка на то, совпадает ли предполагаемый размер матрицы с размером столбца свободных членов
        if (matrix.length != freeArgs.length)
            return false;
        //Провека на то, что матрица квадратная
        if (matrix[0].length != matrix.length)
            return false;
        return true;
    }

    static public boolean checkSLAR (SLAR slar) {
        int[][] matrix = slar.getMatrix();
        int[] freeArgs = slar.getFreeArg();

        //Проверка на то, совпадает ли предполагаемый размер матрицы с размером столбца свободных членов
        if (matrix.length != freeArgs.length)
            return false;
        //Провека на то, что матрица квадратная
        if (matrix[0].length != matrix.length)
            return false;
        return true;
    }

    static public SLAR LineToSLAR (String line) {
        //Разделение строки на массив чисел
        String[] nums = line.trim().split(" ");

        //Проверка правильности ввода СЛАУ
        if (!checkSLAR(line))
            throw new IllegalArgumentException("Неправильный ввод СЛАУ.");

        //Подготовка
        int size = (int) (Math.sqrt(nums.length) - (Math.sqrt(nums.length) % 1));
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

        return new SLAR(matrix, freeArgs);
    }


    ///
     public boolean checkSLARNonStatic (String line) {
        //Разделение строки
        String[] nums = line.trim().split(" ");

        try {

            int size = (int) (Math.sqrt(nums.length) - (Math.sqrt(nums.length) % 1)); //Предполагаемый размер СЛАУ
            int newLen = nums.length - size; // Длина без предполагаемого столбца свободных членов

            //Проверка правильности размера матрицы
            if (Math.sqrt(newLen) % 1 != 0)
                return false;

            //Проверка на правильность типов значений
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

     public boolean checkSLARNonStatic (int[][] generalView) {

        //Проверка на то, что размерность общего вида отличаеться только на 1 строку/столбец
        if ( (generalView.length+1) != generalView[0].length && generalView.length != (generalView[0].length+1))
            return false;

        return true;
    }

     public boolean checkSLARNonStatic (int[][] matrix, int[] freeArgs) {
        //Проверка на то, совпадает ли предполагаемый размер матрицы с размером столбца свободных членов
        if (matrix.length != freeArgs.length)
            return false;
        //Провека на то, что матрица квадратная
        if (matrix[0].length != matrix.length)
            return false;
        return true;
    }

     public SLAR LineToSLARNonStatic (String line) {
        //Разделение строки на массив чисел
        String[] nums = line.trim().split(" ");

        //Проверка правильности ввода СЛАУ
        if (!checkSLAR(line))
            throw new IllegalArgumentException("Неправильный ввод СЛАУ.");

        //Подготовка
        int size = (int) (Math.sqrt(nums.length) - (Math.sqrt(nums.length) % 1));
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

        return new SLAR(matrix, freeArgs);
    }

    public void someFunc() {

    }
}
