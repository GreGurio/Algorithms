package MockitoTest;


import static org.junit.jupiter.api.Assertions.*;

import com.gmail.gurik.model.Matrix;
import com.gmail.gurik.model.SLAR;
import com.gmail.gurik.view.MatrixView;
import com.gmail.gurik.view.SLARview;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Random;

import static org.mockito.Mockito.*;

public class CommandTest {
    Matrix matrix;
    SLAR slar;
    int[][] array;
    int[] freeArgs;


    @BeforeEach
    void setUp() {
        matrix = mock(Matrix.class);
        slar = mock(SLAR.class);
    }

    @Test
    @DisplayName("Тестрирование метода на матрице с рандомным заданием чисел")
    public void TestSolveMatrixWithRandomNums() {
        int matrixSize = 4; // Размер матрицы
        array = new int[matrixSize][matrixSize]; //Массив для матрицы

        Random rn = new Random();
        rn.setSeed(1);

        //Заполнение массива рандомными числами
        for(int i = 0; i < matrixSize; i++) {
            for(int j = 0; j < matrixSize; j++) {
                array[i][j] = rn.nextInt(20);
            }
        }
        //Настройка мока
        when(matrix.getMatrix()).thenReturn(array);

        //Тестрование
        int actual = Matrix.solveMatrix(matrix);
        int expected = 8132;

        assertEquals(expected, actual);

        verify(matrix, times(1)).getMatrix();
    }
    @Test
    @DisplayName("Тестирование метода на матрице с неправильным размером")
    public void TestSolveMatrixWithWrongSize() {
        array = new int[][]{{1,1,1},{1,1,1},{1,1,1},{1,1,1}}; //Матрица неправильного размера 4х3

        //Настройка мока
        when(matrix.getMatrix()).thenReturn(array);


        //Тестрование
        assertThrows(IllegalArgumentException.class, () -> {
            Matrix.solveMatrix(matrix);
        });

        verify(matrix, times(1)).getMatrix();
    }

    @Test
    @DisplayName("Тестирование метода на матрице, которая возвращает 0")
    public void TestSolveMatrixReturnsZero() {
        array = new int[][]{{1,2,3},{4,5,6},{7,8,9}}; //Матрица, определитель которой равен 0

        //Настройка мока
        when(matrix.getMatrix()).thenReturn(array);

        //Тестирование
        int actual = Matrix.solveMatrix(matrix);
        int expected = 0;

        assertEquals(expected, actual);

        verify(matrix, times(1)).getMatrix();
    }

    @Test
    @DisplayName("Тестирование проверки метрицы на квадратной матрице")
    public void TestCheckMatrixWithSquareMatrix() {
        array = new int[][]{{1,2,3},{4,5,6},{7,8,9}}; //Квадратная матрица

        //Создание и настройка матрицы
        when(matrix.getMatrix()).thenReturn(array);

        //Тестрирование
        boolean actual = MatrixView.checkMatrix(matrix);

        assertTrue(actual);

        verify(matrix, times(1)).getMatrix();
    }

    @Test
    @DisplayName("Тестирование проверки метрицы не на квадратной матрице")
    public void TestCheckMatrixWithoutSquareMatrix() {
        array = new int[][]{{1,2},{4,5},{7,8}}; //Квадратная матрица

        //Создание и настройка матрицы
        when(matrix.getMatrix()).thenReturn(array);

        //Тестрирование
        boolean actual = MatrixView.checkMatrix(matrix);

        assertFalse(actual);

        verify(matrix, times(1)).getMatrix();
    }

    @Test
    @DisplayName("Тестрирование метода на СЛАУ с рандомным заданием чисел")
    public void TestSolveSLARwithRandomNums() {
        int matrixSize = 4; // Размер матрицы
        array = new int[matrixSize][matrixSize]; //Массив для матрицы
        freeArgs = new int[matrixSize]; //Массив для столбца свободных членов

        Random rn = new Random();
        rn.setSeed(1);

        //Заполнение массива рандомными числами
        for(int i = 0; i < matrixSize; i++) {
            for(int j = 0; j < matrixSize; j++) {
                array[i][j] = rn.nextInt(20);
            }
            freeArgs[i] = rn.nextInt(20);
        }

        //Настройка мока
        when(slar.getMatrix()).thenReturn(array);
        when(slar.getFreeArg()).thenReturn(freeArgs);

        //Тестрование
        double[] actual = SLAR.solveSLAR(slar);
        double[] expected = { 1.665967570142638, -1.5860002753590012, 0.5088587889778693, 1.1381656022549027 };

        for (int i = 0; i < actual.length; i++) {
            assertEquals(expected[i], actual[i]);
        }

        verify(slar, times(1)).getMatrix();
        verify(slar, times(1)).getFreeArg();
    }

    @Test
    @DisplayName("Тестирование метода решение СЛАУ с неправильным размером матрицы")
    public void TestSolveSlarWithWrongSizeMatrix() {
        int matrixSize = 4; // Размер матрицы
        array = new int[][]{{1,2},{3,4},{5,6}};
        freeArgs = new int[]{1,2,3};


        //Настройка мока
        when(slar.getMatrix()).thenReturn(array);
        when(slar.getFreeArg()).thenReturn(freeArgs);

        //Тестрование

    }

    @Test
    @DisplayName("Тестрирование с использованием квадратной матрицы и правильным размером столбца свободгых членов")
    public void TestCheckSlarWithSquareMatrix() {
        array = new int[][]{{1,2,3},{4,5,6},{7,8,9}}; //Квадратная матрица
        freeArgs = new int[]{1,2,3};

        //Создание и настройка матрицы
        when(slar.getMatrix()).thenReturn(array);
        when(slar.getFreeArg()).thenReturn(freeArgs);

        //Тестрирование
        boolean actual = SLARview.checkSLAR(slar);

        assertTrue(actual);

        verify(slar, times(1)).getMatrix();
        verify(slar, times(1)).getFreeArg();
    }

    @Test
    @DisplayName("Тестирование с использованием не квадратной матрицы")
    public void TestCheckSlarWithoutSquareMatrix() {
        array = new int[][]{{1,2},{4,5},{7,8}}; //Квадратная матрица
        freeArgs = new int[]{1,2,3};

        //Создание и настройка матрицы
        when(slar.getMatrix()).thenReturn(array);
        when(slar.getFreeArg()).thenReturn(freeArgs);

        assertThrows(IllegalArgumentException.class, () -> {
            SLAR.solveSLAR(slar);
        });
    }

    @Test
    @DisplayName("Тестирование с использованием квадратной матрицы, но неправильным размером столбца свободных членов")
    public void TestCheckSlarWithMatrixAndFreeArgSizeNotEqual() {
        array = new int[][]{{1,2,3},{4,5,6},{7,8,9}}; //Квадратная матрица
        freeArgs = new int[]{1,2};

        //Создание и настройка матрицы
        when(slar.getMatrix()).thenReturn(array);
        when(slar.getFreeArg()).thenReturn(freeArgs);

        //Тестрирование
        boolean actual = SLARview.checkSLAR(slar);

        assertFalse(actual);

        verify(slar, times(1)).getMatrix();
        verify(slar, times(1)).getFreeArg();
    }

}
