package com.gmail.gurik.builder;

import com.gmail.gurik.controller.Controller;
import com.gmail.gurik.factory.*;
import com.gmail.gurik.model.Model;
import com.gmail.gurik.model.StandartModel;


public class StandartControllerBuilder extends ControllerBuilder {
    Controller controller;

    public void buildController() {
        controller = new Controller();
    }

    public void buildView() {
        ViewFactory factory = new StandartViewFactory();
        controller.setView(factory.getView());
    }

    public void buildModel() {
        controller.setModel(new StandartModel());
    }

    public void buildModel(Model model) {
        controller.setModel(model);
    }

    public Controller getController() {
        return controller;
    }
}
