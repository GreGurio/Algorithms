package com.gmail.gurik.command;

import com.gmail.gurik.builder.ControllerConstructor;
import com.gmail.gurik.builder.SlarControllerBuilder;
import com.gmail.gurik.controller.Controller;
import com.gmail.gurik.model.Model;

public class SolveSlarCommand extends SolveCommand {
    public SolveSlarCommand(Model model) {
        this.reciever = model;
    }

    public Controller prepare(ControllerConstructor constructor) {
        constructor.setBuilder(new SlarControllerBuilder());
        return constructor.getController();
    }

    public String getMessage() {
        return "-> Enter a SLAR <-   (/exit to quit, /help to more information)";
    }

}
