package com.gmail.gurik;

import com.gmail.gurik.controller.Controller;
import com.gmail.gurik.model.Matrix;
import com.gmail.gurik.model.SLAR;
import com.gmail.gurik.services.ActionService;
import com.gmail.gurik.sources.Messages;
import com.gmail.gurik.view.MatrixView;
import com.gmail.gurik.view.SLARview;

import java.util.Scanner;

import static com.gmail.gurik.services.ShowService.showHelp;

public class Main {
    public static void main(String[] args) {

        System.out.println("--------------------------------------");
        System.out.println("Welcome in the Matrix/SLAR Controller.");
        System.out.println("--------------------------------------");

        Scanner inputScanner = new Scanner(System.in);
        String input;
        Controller<SLARview, SLAR, ActionService> SlarController
                = new Controller<SLARview, SLAR, ActionService>(new SLARview(), new SLAR(), new ActionService());
        Controller<MatrixView, Matrix, ActionService> MatrixController
                = new Controller<MatrixView, Matrix, ActionService>(new MatrixView(), new Matrix(), new ActionService());

        do {
            System.out.println("-> Please enter a command <-");
            input = inputScanner.nextLine();

            if (input.equals("/solve slar")) {
                SlarController.service.SLARchoice(input, inputScanner);
            } else if (input.equals("/solve matrix")) {
                MatrixController.service.MatrixChoice(input, inputScanner);
            } else if (input.equals("/help")) {
                showHelp();
            } else {
                Messages.error("Неизвестная команда.");
            }

        } while (!(input.equals("/exit") || (input.equals("e"))));

        System.out.println("------------------------------");
        System.out.println("Program finished. Best wishes.");
    }
}
