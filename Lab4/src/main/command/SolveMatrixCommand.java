package com.gmail.gurik.command;

import com.gmail.gurik.builder.ControllerConstructor;
import com.gmail.gurik.builder.MatrixControllerBuilder;
import com.gmail.gurik.controller.Controller;
import com.gmail.gurik.model.Model;

public class SolveMatrixCommand extends SolveCommand {

    public SolveMatrixCommand(Model model) {
        this.reciever = model;
    }


    public Controller prepare(ControllerConstructor constructor) {
        constructor.setBuilder(new MatrixControllerBuilder());
        return constructor.getController();
    }

    public String getMessage() {
        return "-> Enter a Matrix <-   (/exit to quit, /help to more information)";
    }
}
