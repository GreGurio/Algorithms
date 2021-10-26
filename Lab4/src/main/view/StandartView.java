package com.gmail.gurik.view;

import com.gmail.gurik.additional.CustomMessages;
import com.gmail.gurik.model.Matrix;
import com.gmail.gurik.model.Model;


public class StandartView implements View {

    public void greeting() {
        System.out.println("--------------------------------------");
        System.out.println("Welcome in the Matrix/SLAR Controller.");
        System.out.println("--------------------------------------");
    }

    public void finishing() {
        System.out.println("------------------------------");
        System.out.println("Program finished. Best wishes.");
    }

    public void help() {
        CustomMessages.yellow("-----------------------------------------------------------------------");
        CustomMessages.yellow("Help:");
        System.out.println("\t" + "/solve matrix - Matrix Calculator. You need to enter a Matrix.");
        System.out.println("\t" + "/solve slar - SLAR Calculator. You need to enter a SLAR.");
        System.out.println("\t" + "/exit - Finishing the program.");
        CustomMessages.yellow("-----------------------------------------------------------------------");
        System.out.println("\n\n");
    }

    public void wrong() {
        CustomMessages.red("Такої команди не існує. Спробуйти ввести ще раз.");
    }

    public void show(Model model) {}

    public Model LineTo(String line) { return new Matrix();}

}
