package com.gmail.gurik.model;

import com.gmail.gurik.additional.ArraysPlus;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import java.util.Arrays;

/**
 * Клас, що репрезентує CЛАУ - систему лінійних алгебраїчних рівнянь.
 * Розширує функціонал класу, що репрезентує матрицю.
 * @author - Гуріненко Андрій, група ТІ-91
 */
public class Slar extends Matrix implements Model {

    private double[] freeArgs;  //Стовбець вільних членів
    private double[] result;    //Результат

    //Конструктор за замовчуванням
    public Slar() {}



    //---------МЕТОДИ ОБЧИСЛЕННЯ СЛАУ----------

    //Вбудований
    /**
     * Метод для обрахунку СЛАУ. В ньому викорисутовуються формули Крамера для обчислення СЛАУ розміру 2 х 2 та 3 х 3.
     * Для СЛАУ більшого розміру використовуєься метод Гауса.
     * Викликається автоматично після ініціалізації матриці та стовбця вільних членів.
     * @throws RuntimeException - внутрішній виняток.
     */
    @Override
    public void solve() throws RuntimeException {
        //Создание возвращаемого массива для результата
        double[] result;
        double[][] matrixCopy = ArraysPlus.copyOf(matrix);
        double[] freeArgsCopy = ArraysPlus.copyOf(freeArgs);


        //Случай матрицы 2х2
        //Решение по формулам Крамера для матрицы 2х2
        if (matrixCopy.length == 2 && matrixCopy[0].length == 2) {
            double[][] arr1 = new double[2][2];
            double[][] arr2 = new double[2][2];

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    arr1[i][j] = matrixCopy[i][j];
                    arr2[i][j] = matrixCopy[i][j];
                }
            }

            for (int i = 0; i < 2; i++) {
                arr1[i][0] = freeArgsCopy[i];
                arr2[i][1] = freeArgsCopy[i];
            }

            result = new double[2];
            result[0] = Matrix.solve(arr1) / Matrix.solve(matrixCopy);
            result[1] = Matrix.solve(arr2) / Matrix.solve(matrixCopy);
            this.result = result;
            return;
        }

        //Случай матрицы 3х3
        //Решение по формулам Крамера для матрицы 3х3
        if (matrixCopy.length == 3 && matrixCopy[0].length == 3) {
            //Решение по формулам Крамера

            double[][] D1 = new double[3][3];
            double[][] D2 = new double[3][3];
            double[][] D3 = new double[3][3];

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    D1[i][j] = matrixCopy[i][j];
                    D2[i][j] = matrixCopy[i][j];
                    D3[i][j] = matrixCopy[i][j];
                }
            }

            for(int i = 0; i < 3; i++) {
                D1[i][0] = freeArgsCopy[i];
                D2[i][1] = freeArgsCopy[i];
                D3[i][2] = freeArgsCopy[i];
            }

            result = new double[3];
            result[0] = Matrix.solve(D1) / Matrix.solve(matrixCopy);
            result[1] = Matrix.solve(D2) / Matrix.solve(matrixCopy);
            result[2] = Matrix.solve(D3) / Matrix.solve(matrixCopy);
            this.result =  result;
            return;
        }

        //Метод Гаусса
        //Создание ступенчатой матрицы

        for (int i = 1; i < matrixCopy.length; i++)
        {
            for (int k = 0; k < i; k++)
            {
                if(matrixCopy[i][k] == 0)
                    continue;
                double multiplier = matrixCopy[i][k]/matrixCopy[k][k];
                boolean ifChangeSymbol = ((matrixCopy[i][k] > 0 && matrixCopy[k][k] > 0)
                        || (matrixCopy[i][k] < 0 && matrixCopy[k][k] < 0));
                if(ifChangeSymbol)
                {
                    for (int j = k; j < matrixCopy[i].length; j++)
                    {
                        matrixCopy[i][j] = matrixCopy[i][j] + (-1) * matrixCopy[k][j] * (multiplier);
                    }
                    freeArgsCopy[i] = freeArgsCopy[i] + (-1) * freeArgsCopy[k] * (multiplier);
                }
                else
                {
                    for (int j = k; j < matrixCopy[i].length; j++)
                    {
                        matrixCopy[i][j] = matrixCopy[i][j] + matrixCopy[k][j] * (multiplier);
                    }
                    freeArgsCopy[i] = freeArgsCopy[i] + freeArgsCopy[k] * (multiplier);
                }
            }
        }

        //Подготовка результата
        //Переинициализация массива для результата
        result = new double[matrixCopy[0].length];

        //Заполнение массива решениями СЛАУ
        for (int i = matrixCopy[0].length; i > 0; i--)
        {
            if(i == matrixCopy[0].length)
                result[i-1] = freeArgsCopy[i-1] / matrixCopy[i-1][i-1];
            else {
                //Переменная, представляющее целое число полученное в результате переноса всех целых чисел в правую
                //сторону от знака равенства
                double rightSideInt = freeArgsCopy[i-1];
                for (int j = 0; j < matrixCopy[0].length - i; j++)
                    rightSideInt += (-1) * matrixCopy[i-1][matrixCopy.length - 1 - j] * result[matrixCopy[0].length - j -1];
                result[i-1] = rightSideInt / matrixCopy[i-1][i-1];
            }
        }
        this.result = result;
    }

    //Статичний Int
    /**
     * Статичний метод для обрахунку СЛАУ, що складається з масивів цілих чисел.
     * @param matrixInteger - Двовимірний масив цілих чисел, що є репрезентацією математичної матриці.
     * @param freeArgsInteger - Одновимірний масив цілих чисел, що є репрезентацією математичного стовбця вільних
     *                        членів у СЛАУ.
     * @return - Повертає масив розв'язків СЛАУ типу "з плаваючою точкою".
     */
    @Deprecated
    public static double[] solveSlar(int[][] matrixInteger, int[] freeArgsInteger) {
        Slar slar = new Slar();
        slar.setMatrix(ArraysPlus.intToDouble(matrixInteger));
        slar.setFreeArgs(ArraysPlus.intToDouble(freeArgsInteger));
        return slar.getResult();
    }

    //Статичний Double
    /**
     * Статичний метод для обрахунку СЛАУ, що складається з масивів чисел з плаваючою точкою.
     * @param matrix - Двовимірний масив чисел з плаваючою точкою, що є репрезентацією математичної матриці.
     * @param freeArg - Одновимірний масив чисел з плаваючою точкою, що є репрезентацією математичного стовбця вільних
     *                        членів у СЛАУ.
     * @return - Повертає масив розв'язків СЛАУ типу "з плаваючою точкою".
     */
    @Deprecated
    public static double[] solveSlar(double matrix[][], double[] freeArg) {
        Slar slar = new Slar();
        slar.setMatrix(matrix);
        slar.setFreeArgs(freeArg);
        return slar.getResult();
    }


    //---------ПЕРЕВІРКИ--------

    /* Если определитель матрицы равен нулю или количество переменных больше количества строк СЛАУ, то
     * Система несовместна или имеет бесконечное количество решений. --mathprofi.ru-- */

    /**
     * Метод для перевірки того, що СЛАУ "совместна и имеет единственное решение" - mathprofi.ru
     * Інакше вона не має розв'язків або має їх нескінченну кількість.
     * @param matrix - Матриця цілих чисел, яка необхідна для перевіки.
     * @return - true, якщо матриця "совместна и имеет единственно решение" - інакше false.
     */
    public static boolean isCorrect(int[][] matrix) {
        return Matrix.solve(matrix) != 0 && matrix.length >= matrix[0].length;
    }

    /**
     * Метод для перевірки того, що СЛАУ "совместна и имеет единственное решение" - mathprofi.ru
     * Інакше вона не має розв'язків або має їх нескінченну кількість.
     * @param matrix - Матриця чисел з плаваючою точкою, яка необхідна для перевіки.
     * @return - true, якщо матриця "совместна и имеет единственно решение" - інакше false.
     */
    public static boolean isCorrect(double[][] matrix) {
        return Matrix.solve(matrix) != 0 && matrix.length >= matrix[0].length;
    }

    /**
     * Метод для перевірки того, що СЛАУ "совместна и имеет единственное решение" - mathprofi.ru
     * Інакше вона не має розв'язків або має їх нескінченну кількість.
     * @param line - Строка, яка є репрезентацією СЛАУ.
     * @return - true, якщо матриця "совместна и имеет единственно решение" - інакше false.
     */
    public static boolean isCorrect(String line) {

        String[] nums = line.trim().split(" ");
        int size = (int)Math.sqrt(nums.length);
        double[][] matrix = new double[size][size];
        int c = 0;

        //Перевірка того, що матриця квадратна
        if (Math.sqrt(nums.length - size) % 1 != 0)
            return false;

        //Створення масиву, що репрезентує матрицю
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = Double.parseDouble(nums[c]);
                c++;
            }
        }

        //Перевірка того, що СЛАУ "совместна и имеет единственное решение"
        if (!isCorrect(matrix))
            return false;

        return true;
    }

    //Getters
    @Override
    @Getter
    public double[] getResult() {
        return this.result;
    }

    @Override
    @Getter
    public double[][] getMatrix() {
        return this.matrix;
    }

    @Getter
    public double[] getFreeArgs() {
        return this.freeArgs;
    }


    //Setters
    @Override
    @Setter
    public void setMatrix(double[][] matrix) {
        //Проверка на то, что СЛАУ совместна и имеет еднственное решение
        if(!Slar.isCorrect(matrix))
            throw new RuntimeException("СЛАУ несовместна или имеет бесконечное количество решений.");
        this.matrix = matrix;
        if(freeArgs != null)
            this.solve();
    }
    @Override
    @Setter
    public void setMatrix(int[][] matrix) {
        //Проверка на то, что СЛАУ совместна и имеет еднственное решение
        if(!Slar.isCorrect(matrix))
            throw new RuntimeException("СЛАУ несовместна или имеет бесконечное количество решений.");
        this.matrix = ArraysPlus.intToDouble(matrix);
        if (freeArgs != null)
            this.solve();
    }

    @Setter
    public void setFreeArgs(double[] freeArgs) {
        this.freeArgs = freeArgs;
        if (matrix != null)
            this.solve();
    }
    @Setter
    public void setFreeArgs(int[] freeArgs) {
        this.freeArgs = ArraysPlus.intToDouble(freeArgs);
        if (matrix != null)
            this.solve();
    }

    //Стандартні віртуальні функції
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Slar slar = (Slar) o;
        return Arrays.equals(matrix, slar.matrix) && Arrays.equals(freeArgs, slar.freeArgs) && Arrays.equals(result, slar.result);
    }

    @Override
    public int hashCode() {
        int result1 = super.hashCode();
        result1 = 31 * result1 + Arrays.hashCode(matrix);
        result1 = 31 * result1 + Arrays.hashCode(freeArgs);
        result1 = 31 * result1 + Arrays.hashCode(result);
        return result1;
    }

    @Override
    public String toString() {
        return "Slar{" +
                "matrix=" + Arrays.toString(matrix) +
                ", freeArgs=" + Arrays.toString(freeArgs) +
                ", result=" + Arrays.toString(result) +
                '}';
    }
}
