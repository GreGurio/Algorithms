package JUnit;

import com.gmail.gurik.model.SLAR;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class SLARTest {
    int[][] array;
    int[][] sqareArray;
    int[][] sqareArray2;
    int[][] sqareArray3;
    int[] freeArgs;
    int[] freeArgs1;
    SLAR slar;
    SLAR slar1;//Такой же, как slar
    SLAR slar2;//Отличающийся от slar и slar1

    @Rule
    public TestRule timeout = new Timeout(1000);

    @BeforeEach
    void setUp() {
        array = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {3, 4, 5, 6}};
        sqareArray = new int[][]{{5,15,6},{3,0,10},{7,23,1}};
        sqareArray2 = new int[][]{{6,15,6},{3,0,10},{7,23,1}};//Отличаеться от squareArray
        sqareArray3 = new int[][]{{1,2,3},{4,5,6},{7,8,9}}; //Определитель равен 0
        freeArgs = new int[]{1,2,3};
        freeArgs1 = new int[]{1,2};
        slar = new SLAR(sqareArray, freeArgs);
        slar1 = new SLAR(sqareArray, freeArgs);//Такой же, как slar
        slar2 = new SLAR(sqareArray2, freeArgs);//Отличающийся
    }

    @Test
    @DisplayName("Constructor Exception Test")
    void constructorException() {
        assertThrows(IllegalArgumentException.class, () -> {
            SLAR slar = new SLAR(array, freeArgs1);
        });
    }

    @Test
    @DisplayName("New test for solvation SLAR function using Hauss method")
    void solveSLAR() {
        int[][] matrix = {{1,6,-5,15,7},{3,-10,9,1,8},{12,4,6,-8,1},{5,1,9,-5,1},{4,3,8,10,2}};
        int[] freeArg = {12,5,-4,1,4};

        double[] actual = SLAR.solveSLAR(matrix, freeArg);

        double[] expected = {
                -0.9080000000000039,
                0.7520000000000021,
                0.3400000000000013,
                -0.040000000000001236,
                1.5280000000000022};

        assertArrayEquals(actual, expected);
    }

    @Test
    @DisplayName("New test for solvation SLAR function using Kramer's formulas for 2x2")
    void solveSLAR2() {
        int[][] matrix = {{7,1},{-5,3}};
        int[] freeArg = {23,1};

        double[] actual = SLAR.solveSLAR(matrix, freeArg);

        double[] expected = {2.6153846153846154, 4.6923076923076925};

        assertArrayEquals(actual, expected);
    }
    @Test
    @DisplayName("New test for solvation SLAR function using Kramer's formulas for 3x3")
    void solveSLAR3() {
        int[][] matrix = {{3,-2, 4},{3,4,-2}, {2, -1, -1}};
        int[] freeArg = {21, 9, 10};

        double[] actual = SLAR.solveSLAR(matrix, freeArg);

        double[] expected = {5.0, -1.0, 1.0};

        assertArrayEquals(actual, expected);
    }

    @Test
    @DisplayName("New test for solvation SLAR Illegal Arguments Exception where there're more arguments than rows")
    void solveSLARArgumentException1() {
        assertThrows(IllegalArgumentException.class, () -> {
            SLAR.solveSLAR(array, freeArgs);
        });
    }

    @Test
    @DisplayName("New test for solvation SLAR Illegal Arguments Exception when identifier equals zero")
    void solveSLARArgumentException2() {
        assertThrows(IllegalArgumentException.class, () -> {
            SLAR.solveSLAR(sqareArray3, freeArgs);
        });
    }

    @Test
    @DisplayName("New test for virtual function of equal")
    void testEquals() {
        assertTrue(slar.equals(slar1));
    }

    @Test
    @DisplayName("New test for virtual function of equal where it's false")
    void testNotEquals() {
        assertFalse(slar.equals(slar2));
    }

    @Test
    @DisplayName("New test for getters and setters")
    void gettersSettersTest() {
        assertEquals(slar.getFreeArg(), freeArgs);
        slar.setMatrix(sqareArray2);
        assertEquals(slar.getMatrix(), sqareArray2);
        slar.setFreeArg(freeArgs);
        assertEquals(slar.getFreeArg(), freeArgs);
    }
}