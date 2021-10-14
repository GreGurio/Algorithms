package com.gmail.gurik.services;

import com.gmail.gurik.model.Matrix;
import com.gmail.gurik.model.SLAR;
import com.gmail.gurik.sources.Messages;

import java.util.Scanner;

import static com.gmail.gurik.services.ShowService.*;
import static com.gmail.gurik.view.MatrixView.*;
import static com.gmail.gurik.view.SLARview.*;





public class ActionService {
    public void MatrixChoice(String input, Scanner inputScanner) {
        //Введение и проверка Матрицы на правильность
        do {
            System.out.println("-> Enter a Matrix <-   (/stop to quit, /info to more information)");
            input = inputScanner.nextLine();

            //Информация по вводу
            if (input.equals("/info")) {
                showMatrixInfo();
            }

            if (!checkMatrix(input) && !input.equals("/info") && !input.equals("/stop"))
                Messages.error("Неверный ввод. Попробуйте ещё раз.");
        } while(!checkMatrix(input) && !input.equals("/stop"));

        if (!input.equals("/stop")) {
            //Вывод введённой матрицы на экран
            System.out.println("--MATRIX--");
            Matrix matrix = LineToMatrix(input);
            showMatrix(matrix);

            //Подсчёт матрицы и вывод результата на экран
            System.out.println("RESULT: " + matrix.getIdentifier() + "\n" +
                    "-------------------" +
                    "\n\n");
        }
    }

    public void SLARchoice(String input, Scanner inputScanner) {
        //Введение и проверка СЛАУ на правильность
        do {
            System.out.println("-> Enter a SLAR <-   (/stop to quit, /info to more information)");
            input = inputScanner.nextLine();

            //Информация по вводу
            if (input.equals("/info")) {
                showSLARInfo();
            }

            if (!checkSLAR(input) && !input.equals("/info") && !input.equals("/stop"))
                Messages.error("Неверный ввод. Попробуйте ещё раз.");
        } while(!checkSLAR(input) && !input.equals("/stop"));

        if (!input.equals("/stop")) {

            SLAR slar = LineToSLAR(input);

            //Вывод введенной СЛАУ на экран
            System.out.println("--SLAR--");
            showSLAR(slar);

            //Подсчёт результата СЛАУ и вывод на экран
            double[] resultSLAR = SLAR.solveSLAR(slar.getMatrix(), slar.getFreeArg());
            System.out.println("RESULT: ");
            for (int i = 0; i < resultSLAR.length; i++) {
                System.out.println("\tx" + (i + 1) + " = " + resultSLAR[i]);
            }
            System.out.println("----------------");
            System.out.println("\n\n");
        }
    }
}
