package com.gmail.kpi;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

//Интерфейс
interface SLARinterface {
    int[] solveSLAR(int[][] matrix, int[] freeArg);
}

public class SLAR extends Matrix implements SLARinterface {
    private int[][] matrix; // Матрица
    private int[] freeArg; //Массив(столбец) свободных членов

    //Конструктор по умолчанию
    public SLAR() {
        freeArg = new int[0];
        matrix = new int[0][0];
        identifier = 0;
    }

    //Конструктор с параметрами
    public SLAR(int[] freeArg, int[][] matrix) throws SLARexception {
        if (freeArg.length != 3 && matrix.length != 3 && matrix[0].length != 3)
            throw new SLARexception("СЛАУ или столбец свободных членов не подходит по размеру. Они должны быть формата 3х3 и 3х1 соответственно.");
        this.matrix = matrix;
        this.freeArg = freeArg;
    }

    //Функция решения СЛАУ
    public int[] solveSLAR(int[][] matrix, int[] freeArg) {
        int idenRes = solveMatrix(matrix);
        int a11 = (-1)^2 * (matrix[1][1]*matrix[2][2]-matrix[2][1]*matrix[1][2]);
        int a12 = (-1)^3 * (matrix[0][1]*matrix[2][2]-matrix[2][1]*matrix[0][2]);
        int a13 = (-1)^4 * (matrix[0][1]*matrix[1][2]-matrix[1][1]*matrix[0][2]);
        int a21 = (-1)^3 * (matrix[1][0]*matrix[2][2]-matrix[2][0]*matrix[1][2]);
        int a22 = (-1)^4 * (matrix[0][0]*matrix[2][2]-matrix[2][0]*matrix[0][2]);
        int a23 = (-1)^5 * (matrix[0][0]*matrix[1][2]-matrix[1][0]*matrix[0][2]);
        int a31 = (-1)^4 * (matrix[1][0]*matrix[2][1]-matrix[2][0]*matrix[1][1]);
        int a32 = (-1)^5 * (matrix[0][0]*matrix[2][1]-matrix[0][1]*matrix[2][0]);
        int a33 = (-1)^6 * (matrix[0][0]*matrix[1][1]-matrix[1][0]*matrix[0][1]);
        int x1 = a11*freeArg[0]+a12*freeArg[1]+a13*freeArg[2];
        int x2 = a21*freeArg[0]+a22*freeArg[1]+a23*freeArg[2];
        int x3 = a31*freeArg[0]+a32*freeArg[1]+a33*freeArg[2];
        int[] res = {x1 ,x2, x3};
        return res;
    }

    //Геттеры и сеттеры
    @Getter
    public int[] getFreeArg() {
        return freeArg;
    }
    @Setter
    public void setFreeArg(int[] freeArg) throws SLARexception {
        if (freeArg.length != 3)
            throw new SLARexception("Столбец свободных членов не подходит по размеру. Он должен быть формата 3х1.");
        this.freeArg = freeArg;
    }
    @Setter
    public void setMatrix(int[][] matrix) throws SLARexception {
        if (matrix.length != 3 && matrix[0].length != 3)
            throw new SLARexception("Матрица не подходит по размеру. Она должна быть формата 3х3.");
        this.matrix = matrix;
    }
    //Виртуальный метод toString()
    @Override
    public String toString() {
        String str = "SLAR{\n" +
                "\tmatrix=\n";
        /*Вывод матрицы*/
        for (int[] ints : matrix) {
            str += "\t\t[";
            for (int j = 0; j < matrix.length; j++) {
                str += "" + ints[j];
                if (j != matrix.length - 1)
                    str += ',';
            }
            str += "]\n";
        }
        /*-----------*/
        str += "freeArg=[";
        for(int i = 0; i < freeArg.length; i++) {
            str += "" + freeArg[i];
            if (i != freeArg.length-1)
                str += ',';
        }
        str += "]\n";
        return str;
    }
}
