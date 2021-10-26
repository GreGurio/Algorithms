package com.gmail.gurik.model;

import com.gmail.gurik.additional.ArraysPlus;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import java.util.Arrays;
import java.util.Objects;

/**
 * Клас, що репрезентує Матрицю.
 * @author - Гуріненко Андрій, група ТІ-91
 */

public class Matrix implements Model {
    protected double[][] matrix;  // Матриця
    private double identifier;  // Визначник

    //Конструктор за замовчуванням
    public Matrix() { }

    //---------МЕТОДИ ОБЧИСЛЕННЯ МАТРИЦІ----------

    // Вбудована

    /**
     * Метод для обрахунку визначника матриці. Для обрахунку використовується розкладання матриці на мінори.
     * Математична база - mathprofi.ru
     * @throws RuntimeException - Виняток, якщо задана неквадратна матриця. Визначний такої матриці не може бути
     * обрахованим.
     */
    public void solve() throws RuntimeException {
        if(!Matrix.isSquare(matrix))
            throw new RuntimeException("Матриця повинна бути квадратною.");

        if (matrix.length == 2)
            this.identifier =  (int)matrix[0][0]*matrix[1][1] - matrix[0][1]*matrix[1][0];
        else {
            int res = 0;
            for (int i = 0; i < matrix.length; i++) {
                /*Створення похідної матриці*/
                double[][] matrix_inner = new double[matrix.length-1][matrix.length-1];
                for (int j = 0; j < matrix.length-1; j++) {
                    int k, m;
                    for (k = 0, m = 0; k < matrix.length-1; k++, m++) {
                        if (k == i) {
                            m++;
                        }
                        matrix_inner[j][k] = matrix[j+1][m];
                    }
                }
                /*------------------------*/
                if ((i+1)%2==0)
                    res += matrix[0][i] * solve(matrix_inner);
                else
                    res -= matrix[0][i] * solve(matrix_inner);
            }
            this.identifier = res;
        }
    }

    // Статична Integer

    /**
     * Статичний метод для обрахунку визначника матриці.
     * @param array - Двовимірний масив цілих чисел, що є репрезентацією математичної матриці.
     * @return - Повертає ціле число - розв'язок визначника.
     * @throws RuntimeException - Виняток, якщо задана неквадратна матриця. Визначник такої матриці не може бути
     * обрахованим.
     */
    @Deprecated
    public static int solve(int[][] array) throws RuntimeException {
        Matrix matrix = new Matrix();
        matrix.setMatrix(array);
        return (int)matrix.getResult()[0];
    }

    // Статична Double

    /**
     * Статичний метож для обрахунку визначника матриці.
     * @param array - Двовимірний масив чисел з плаваючою точкою, що є репрезентацією математичної матриці.
     * @return - Повертає ціле число - розв'язок визначника.
     * @throws RuntimeException - Виняток, якщо задана неквадратна матриця. Визначник такої матриуі не може бути
     * обрахованим.
     */
    @Deprecated
    public static double solve(double[][] array) throws RuntimeException {
        Matrix matrix = new Matrix();
        matrix.setMatrix(array);
        return (int)matrix.getResult()[0];
    }



    //---------ПЕРЕВІРКИ--------

    /**
     * Перевірка того, що математична матриця є квадратною.
     * @param matrix - Двовимірний масив чисел з плаваючою точкою, що є репрезентацією математичної матриці.
     * @return - Повертає true, якщо матриця квадратна, інакше false.
     */
    public static boolean isSquare(double[][] matrix) {
        return matrix.length == matrix[0].length;
    }

    /**
     * Перевірка того, що математична матриця є квадратною.
     * @param matrix - Двовимірний масив цілих чисел, що є репрезентацією математичної матриці.
     * @return - Повертає true, якщо матриця квадратна, інакше false.
     */
    public static boolean isSquare(int[][] matrix) {
        return matrix.length == matrix[0].length;
    }

    /**
     * Перевірка того, що математична матриця є квадратною.
     * @param line - Строка, що є репрезентацією математичної матриці.
     * @return - Повертає true, якщо матриця квадратна, інакше false.
     */
    public static boolean isSquare(String line) {
        String[] nums = line.trim().split(" ");
        return Math.sqrt(nums.length) % 1 == 0;
    }

    //Getters
    @Getter
    public double[] getResult() {
        return new double[]{identifier};
    }
    @Getter
    public double[][] getMatrix() {
        return matrix;
    }

    //Setters
    @Setter
    public void setMatrix(double[][] matrix) throws RuntimeException {
        if(!Matrix.isSquare(matrix))
            throw new RuntimeException("Матриця має бути квадратною.");

        this.matrix = matrix;
        this.solve();
    }
    @Setter
    public void setMatrix(int[][] matrix) throws RuntimeException {
        if(!Matrix.isSquare(matrix))
            throw new RuntimeException("Матриця має бути квадратною.");

        this.matrix = ArraysPlus.intToDouble(matrix);
        this.solve();
    }


    //Стандартні віртуальні методи
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix1 = (Matrix) o;
        return identifier == matrix1.identifier && Arrays.equals(matrix, matrix1.matrix);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(identifier);
        result = 31 * result + Arrays.hashCode(matrix);
        return result;
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "matrix=" + Arrays.toString(matrix) +
                ", identifier=" + identifier +
                '}';
    }
}
