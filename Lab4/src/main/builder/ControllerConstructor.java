package com.gmail.gurik.builder;

import com.gmail.gurik.controller.Controller;


public class ControllerConstructor {

    ControllerBuilder builder;

    public void setBuilder(ControllerBuilder builder) {
        this.builder = builder;
    }

    public Controller getController() {
        builder.buildController();
        builder.buildView();
        builder.buildModel();
        return builder.getController();
    }
}
