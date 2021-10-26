package com.gmail.gurik.command;

import com.gmail.gurik.builder.ControllerConstructor;
import com.gmail.gurik.builder.MatrixControllerBuilder;
import com.gmail.gurik.controller.Controller;
import com.gmail.gurik.model.Model;
import com.gmail.gurik.model.StandartModel;

abstract class SolveCommand implements Command {
    Model reciever;

    public void execute() {
        ControllerConstructor constructor = new ControllerConstructor();
        String message = getMessage();

        Controller controller = prepare(constructor);
        reciever = new StandartModel(controller, message);

        reciever.solve();
    }

    public Controller prepare(ControllerConstructor constructor) {
        return new Controller();
    }

    public String getMessage() {
        return "";
    }
}
