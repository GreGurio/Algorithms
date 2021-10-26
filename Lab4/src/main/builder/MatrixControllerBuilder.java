package com.gmail.gurik.builder;

import com.gmail.gurik.controller.Controller;
import com.gmail.gurik.controller.MatrixController;
import com.gmail.gurik.factory.MatrixViewFactory;
import com.gmail.gurik.factory.ViewFactory;
import com.gmail.gurik.model.Matrix;
import com.gmail.gurik.model.Model;


public class MatrixControllerBuilder extends ControllerBuilder {

    public void buildController() {
        controller = new MatrixController();
    }

    public void buildView() {
        ViewFactory factory = new MatrixViewFactory();
        controller.setView(factory.getView());
    }

    public void buildModel() {
        controller.setModel(new Matrix());
    }

    public void buildModel(Model model) {
        controller.setModel(model);
    }

    public Controller getController() {
        return controller;
    }
}
