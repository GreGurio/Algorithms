package com.gmail.gurik.builder;

import com.gmail.gurik.controller.Controller;
import com.gmail.gurik.controller.SlarController;
import com.gmail.gurik.factory.*;
import com.gmail.gurik.model.Model;
import com.gmail.gurik.model.Slar;


public class SlarControllerBuilder extends ControllerBuilder {

    public void buildController() {
        controller = new SlarController();
    }
    public void buildView() {
        ViewFactory factory = new SlarViewFactory();
        controller.setView(factory.getView());
    }
    public void buildModel() {
        controller.setModel(new Slar());
    }

    public void buildModel(Model model) {
        controller.setModel(model);
    }

    public Controller getController() {
        return controller;
    }
}
