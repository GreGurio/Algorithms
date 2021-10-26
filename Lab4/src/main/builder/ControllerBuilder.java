package com.gmail.gurik.builder;

import com.gmail.gurik.controller.Controller;
import com.gmail.gurik.model.Model;


public abstract class ControllerBuilder {

    Controller controller;

    abstract void buildController();

    abstract void buildView();

    abstract void buildModel();

    abstract void buildModel(Model model);

    abstract Controller getController();
}
