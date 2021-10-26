package com.gmail.gurik;

import com.gmail.gurik.builder.ControllerConstructor;
import com.gmail.gurik.builder.StandartControllerBuilder;
import com.gmail.gurik.controller.Controller;


public class MainRunner {

    public static void main(String[] args) {
        try {
            Logger.getLogger().add("[SESSION STARTED]");

            ControllerConstructor controllerConstructor = new ControllerConstructor();
            controllerConstructor.setBuilder(new StandartControllerBuilder());
            Controller controller = controllerConstructor.getController();

            controller.getView().greeting();

            controller.userInteraction("-> Please enter a command <-");

            controller.getView().finishing();

            Logger.getLogger().add("[SESSION FINISHED]");

        } catch (Exception e) {
            Logger.getLogger().add(" - [EXCEPTION] - " + e.getMessage());
        }
    }

}
