import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {
    Matrix matrix;
    Matrix matrix1;//Идентичный matrix
    Matrix matrix2;//Отличающийся
    int[][] array;
    int[][] sqareArray;

    @Rule
    public TestRule timeout = new Timeout(1000);

    @BeforeEach
    void setUp() throws Exception {
        array = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {3, 4, 5, 6}};
        sqareArray = new int[][]{{5,15,6,8},{3,0,10,4},{7,23,1,9},{2,8,6,5}};
        matrix = new Matrix(sqareArray);
        matrix1 = new Matrix(sqareArray);//Идентичный
        matrix2 = new Matrix(array);//Отличающийся
    }



    @Test
    @DisplayName("New test for solvation Matrix function")
    void solveMatrix() {
        int actual = Matrix.solveMatrix(sqareArray);

        int expected = -101;

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("New test for solvation Matrix Illegal Arguments Exception")
    void solveMatrixArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Matrix.solveMatrix(array);
        });
    }

    @Test
    @DisplayName("New test for virtual function of equal")
    void testEquals() {
        assertTrue(matrix.equals(matrix1));
    }

    @Test
    @DisplayName("New test for virtual function of equal where it's false")
    void testNotEquals() {
        assertFalse(matrix.equals(matrix2));
    }
}