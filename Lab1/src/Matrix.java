package com.gmail.kpi;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;


//Интерфейс
interface MatrixInterface {
    int solveMatrix(int[][] matrix);
    String toString();
    void setIdentifier(int identifier);
}

public class Matrix implements MatrixInterface {
    protected int[][] matrix; //Матрица
    protected int identifier; //Определитель

    //Конструктор по умолчанию
    public Matrix() {
        matrix = new int[0][0];
        identifier = 0;
    }
    //Конструктор с параметрами без определителя
    public Matrix(int[][] matrix) {
        this.matrix = matrix;
    }
    //Конструктор с параметрами с определителем
    public Matrix(int[][] matrix, int identifier) {
        this.matrix = matrix;
        this.identifier = identifier;
    }

    //Функция вычисления определителя
    public int solveMatrix(int[][] matrix) {
        if (matrix.length == 2)
            return matrix[0][0]*matrix[1][1] - matrix[0][1]*matrix[1][0];
        else {
            int res = 0;
            for (int i = 0; i < matrix.length; i++) {
                /*Создание второй матрицы*/
                int[][] matrix_inner = new int[matrix.length-1][matrix.length-1];
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
                    res += matrix[0][i] * solveMatrix(matrix_inner);
                else
                    res -= matrix[0][i] * solveMatrix(matrix_inner);
            }
            return res;
        }
    }


    //Геттеры и Сеттеры
    @Getter
    public int[][] getMatrix() {
        return matrix;
    }
    @Setter
    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
    @Getter
    public int getIdentifier() {
        return identifier;
    }
    @Setter
    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    @Override
    @CustomAnnotation(expected = Matrix.class)
    public String toString() {
        String str = "Matrix{\n" +
                        "\tmatrix=\n";
        /*Вывод матрицы*/
        for(int i = 0; i < matrix.length; i++) {
            str += "\t\t[";
            for (int j = 0; j < matrix.length; j++) {
                str += "" + matrix[i][j];
                if (j != matrix.length-1)
                    str += ',';
            }
            str += "]\n";
        }
        /*-----------*/
        str += "\tidentifier=" + identifier + "\n}";
        return str;
    }
}
