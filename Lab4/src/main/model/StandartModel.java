package com.gmail.gurik.model;

import com.gmail.gurik.controller.Controller;

public class StandartModel implements Model {
    Controller controller;
    String message;

    public StandartModel() {}

    public StandartModel(Controller controller, String message) {
        this.controller = controller;
        this.message = message;
    }

    public void solve() {
        controller.getView().greeting();
        controller.userInteraction(message);
        controller.getView().finishing();
    }






    @Deprecated
    public double[] getResult() {
        return new double[0];
    }
}
