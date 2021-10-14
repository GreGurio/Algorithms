package com.gmail.gurik.model;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.util.Arrays;

//Интерфейс
interface SLARinterface {
}

public class SLAR extends Matrix implements SLARinterface {
    private int[][] matrix; // Матрица
    private int[] freeArg; //Массив(столбец) свободных членов

    //Конструктор по умолчанию
    public SLAR () {
        matrix = new int[0][0];
        freeArg = new int[0];
    }

    //Конструктор с параметрами
    public SLAR(int[][] matrix, int[] freeArg) throws IllegalArgumentException {
        if (freeArg.length != matrix.length)
            throw new IllegalArgumentException("CЛАУ задана неверно, поскольку количество строк матрицы не равно" +
                    "количеству строк столбца свободных членов. Их количество должно быть одинаковым.");
        this.matrix = matrix;
        this.freeArg = freeArg;
    }

    //Функция решения СЛАУ
    static public double[] solveSLAR(int[][] matrixInteger, int[] freeArgInteger) {
        //Проверка на то, что СЛАУ совместна и имеет еднственное решение
        /*Если определитель матрицы равен нулю или количество переменных больше количества строк СЛАУ, то
        * Система несовместна или имеет бесконечное количество решений.*/
        if (Matrix.solveMatrix(matrixInteger) == 0 || matrixInteger.length < matrixInteger[0].length)
            throw new IllegalArgumentException("СЛАУ несовместна или имеет бесконечное количество решений.");

        //Создание массивов float
        double[][] matrix = new double[matrixInteger.length][matrixInteger[0].length];
        double[] freeArg = new double[freeArgInteger.length];
        //Создание возвращаемого массива для результата
        double[] result;

        //Копирование элементов старых массивов в новые
        for(int i = 0; i < matrixInteger.length; i++) {
            freeArg[i] = freeArgInteger[i];
            for(int j = 0; j < matrixInteger[0].length; j++)
                matrix[i][j] = matrixInteger[i][j];
        }

        //Случай матрицы 2х2
        //Решение по формулам Крамера для матрицы 2х2
        if (matrix.length == 2 && matrix[0].length == 2) {
            double[][] arr1 = new double[2][2];
            double[][] arr2 = new double[2][2];

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    arr1[i][j] = matrix[i][j];
                    arr2[i][j] = matrix[i][j];
                }
            }

            for (int i = 0; i < 2; i++) {
                arr1[i][0] = freeArg[i];
                arr2[i][1] = freeArg[i];
            }

            result = new double[2];
            result[0] = Matrix.solveMatrix(arr1) / Matrix.solveMatrix(matrix);
            result[1] = Matrix.solveMatrix(arr2) / Matrix.solveMatrix(matrix);
            return result;
        }

        //Случай матрицы 3х3
        //Решение по формулам Крамера для матрицы 3х3
        if (matrix.length == 3 && matrix[0].length == 3) {
            //Решение по формулам Крамера
            double[][] D1 = new double[3][3];
            double[][] D2 = new double[3][3];
            double[][] D3 = new double[3][3];

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    D1[i][j] = matrix[i][j];
                    D2[i][j] = matrix[i][j];
                    D3[i][j] = matrix[i][j];
                }
            }

            for(int i = 0; i < 3; i++) {
                D1[i][0] = freeArg[i];
                D2[i][1] = freeArg[i];
                D3[i][2] = freeArg[i];
            }

            result = new double[3];
            result[0] = Matrix.solveMatrix(D1) / Matrix.solveMatrix(matrix);
            result[1] = Matrix.solveMatrix(D2) / Matrix.solveMatrix(matrix);
            result[2] = Matrix.solveMatrix(D3) / Matrix.solveMatrix(matrix);
            return result;
        }

        //Метод Гаусса
        //Создание ступенчатой матрицы
        for (int i = 1; i < matrix.length; i++)
        {
            for (int k = 0; k < i; k++)
            {
                if(matrix[i][k] == 0)
                    continue;
                double multiplier = matrix[i][k]/matrix[k][k];
                boolean ifChangeSymbol = ((matrix[i][k] > 0 && matrix[k][k] > 0)
                                        || (matrix[i][k] < 0 && matrix[k][k] < 0));
                if(ifChangeSymbol)
                {
                    for (int j = k; j < matrix[i].length; j++)
                    {
                        matrix[i][j] = matrix[i][j] + (-1) * matrix[k][j] * (multiplier);
                    }
                    freeArg[i] = freeArg[i] + (-1) * freeArg[k] * (multiplier);
                }
                else
                    {
                    for (int j = k; j < matrix[i].length; j++)
                    {
                        matrix[i][j] = matrix[i][j] + matrix[k][j] * (multiplier);
                    }
                    freeArg[i] = freeArg[i] + freeArg[k] * (multiplier);
                }
            }
        }

        //Подготовка результата
        //Переинициализация массива для результата
        result = new double[matrix[0].length];

        //Заполнение массива решениями СЛАУ
        for (int i = matrix[0].length; i > 0; i--)
        {
            if(i == matrix[0].length)
                result[i-1] = freeArg[i-1] / matrix[i-1][i-1];
            else {
                //Переменная, представляющее целое число полученное в результате переноса всех целых чисел в правую
                //сторону от знака равенства
                double rightSideInt = freeArg[i-1];
                for (int j = 0; j < matrix[0].length - i; j++)
                    rightSideInt += (-1) * matrix[i-1][matrix.length - 1 - j] * result[matrix[0].length - j -1];
                result[i-1] = rightSideInt / matrix[i-1][i-1];
            }
        }
        return result;
    }

    static public double[] solveSLAR(SLAR slar) {
        //Проверка на то, что СЛАУ совместна и имеет еднственное решение
        /*Если определитель матрицы равен нулю или количество переменных больше количества строк СЛАУ, то
         * Система несовместна или имеет бесконечное количество решений.*/
        int[][] matrixInteger = slar.getMatrix();
        int[] freeArgInteger = slar.getFreeArg();
        if (Matrix.solveMatrix(matrixInteger) == 0 || matrixInteger.length < matrixInteger[0].length)
            throw new IllegalArgumentException("СЛАУ несовместна или имеет бесконечное количество решений.");

        //Создание массивов float
        double[][] matrix = new double[matrixInteger.length][matrixInteger[0].length];
        double[] freeArg = new double[freeArgInteger.length];
        //Создание возвращаемого массива для результата
        double[] result;

        //Копирование элементов старых массивов в новые
        for(int i = 0; i < matrixInteger.length; i++) {
            freeArg[i] = freeArgInteger[i];
            for(int j = 0; j < matrixInteger[0].length; j++)
                matrix[i][j] = matrixInteger[i][j];
        }

        //Случай матрицы 2х2
        //Решение по формулам Крамера для матрицы 2х2
        if (matrix.length == 2 && matrix[0].length == 2) {
            double[][] arr1 = new double[2][2];
            double[][] arr2 = new double[2][2];

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    arr1[i][j] = matrix[i][j];
                    arr2[i][j] = matrix[i][j];
                }
            }

            for (int i = 0; i < 2; i++) {
                arr1[i][0] = freeArg[i];
                arr2[i][1] = freeArg[i];
            }

            result = new double[2];
            result[0] = Matrix.solveMatrix(arr1) / Matrix.solveMatrix(matrix);
            result[1] = Matrix.solveMatrix(arr2) / Matrix.solveMatrix(matrix);
            return result;
        }

        //Случай матрицы 3х3
        //Решение по формулам Крамера для матрицы 3х3
        if (matrix.length == 3 && matrix[0].length == 3) {
            //Решение по формулам Крамера
            double[][] D1 = new double[3][3];
            double[][] D2 = new double[3][3];
            double[][] D3 = new double[3][3];

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    D1[i][j] = matrix[i][j];
                    D2[i][j] = matrix[i][j];
                    D3[i][j] = matrix[i][j];
                }
            }

            for(int i = 0; i < 3; i++) {
                D1[i][0] = freeArg[i];
                D2[i][1] = freeArg[i];
                D3[i][2] = freeArg[i];
            }

            result = new double[3];
            result[0] = Matrix.solveMatrix(D1) / Matrix.solveMatrix(matrix);
            result[1] = Matrix.solveMatrix(D2) / Matrix.solveMatrix(matrix);
            result[2] = Matrix.solveMatrix(D3) / Matrix.solveMatrix(matrix);
            return result;
        }

        //Метод Гаусса
        //Создание ступенчатой матрицы
        for (int i = 1; i < matrix.length; i++)
        {
            for (int k = 0; k < i; k++)
            {
                if(matrix[i][k] == 0)
                    continue;
                double multiplier = matrix[i][k]/matrix[k][k];
                boolean ifChangeSymbol = ((matrix[i][k] > 0 && matrix[k][k] > 0)
                        || (matrix[i][k] < 0 && matrix[k][k] < 0));
                if(ifChangeSymbol)
                {
                    for (int j = k; j < matrix[i].length; j++)
                    {
                        matrix[i][j] = matrix[i][j] + (-1) * matrix[k][j] * (multiplier);
                    }
                    freeArg[i] = freeArg[i] + (-1) * freeArg[k] * (multiplier);
                }
                else
                {
                    for (int j = k; j < matrix[i].length; j++)
                    {
                        matrix[i][j] = matrix[i][j] + matrix[k][j] * (multiplier);
                    }
                    freeArg[i] = freeArg[i] + freeArg[k] * (multiplier);
                }
            }
        }

        //Подготовка результата
        //Переинициализация массива для результата
        result = new double[matrix[0].length];

        //Заполнение массива решениями СЛАУ
        for (int i = matrix[0].length; i > 0; i--)
        {
            if(i == matrix[0].length)
                result[i-1] = freeArg[i-1] / matrix[i-1][i-1];
            else {
                //Переменная, представляющее целое число полученное в результате переноса всех целых чисел в правую
                //сторону от знака равенства
                double rightSideInt = freeArg[i-1];
                for (int j = 0; j < matrix[0].length - i; j++)
                    rightSideInt += (-1) * matrix[i-1][matrix.length - 1 - j] * result[matrix[0].length - j -1];
                result[i-1] = rightSideInt / matrix[i-1][i-1];
            }
        }
        return result;
    }


    //Геттеры и сеттеры
    @Getter
    public int[] getFreeArg() {
        return freeArg;
    }
    @Getter
    public  int[][] getMatrix() {
        return matrix;
    }
    @Setter
    public void setFreeArg(int[] freeArg) {
        if (freeArg.length != 3)
            throw new IllegalArgumentException("Столбец свободных членов не подходит по размеру. Он должен быть формата 3х1.");
        this.freeArg = freeArg;
    }
    @Setter
    public void setMatrix(int[][] matrix) {
        if (matrix.length != 3 && matrix[0].length != 3)
            throw new IllegalArgumentException("Матрица не подходит по размеру. Она должна быть формата 3х3.");
        this.matrix = matrix;
    }


    //Виртуальные методы
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SLAR slar = (SLAR) o;
        return Arrays.equals(matrix, slar.matrix) &&
                Arrays.equals(freeArg, slar.freeArg);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(matrix);
        result = 31 * result + Arrays.hashCode(freeArg);
        return result;
    }

    @Override
    public String toString() {
        return "SLAR{" +
                "matrix=" + Arrays.toString(matrix) +
                ", freeArg=" + Arrays.toString(freeArg) +
                '}';
    }
}
