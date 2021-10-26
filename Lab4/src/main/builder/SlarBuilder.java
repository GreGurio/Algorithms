package com.gmail.gurik.builder;

import com.gmail.gurik.model.Model;
import com.gmail.gurik.model.Slar;


public class SlarBuilder extends Builder {

    public void buildModel() { model = new Slar(); }

    public void buildMatrix(int[][] array) {
        ((Slar)model).setMatrix(array);
    }

    public void buildMatrix(double[][] array) {
        ((Slar)model).setMatrix(array);
    }

    public void buildSecond(int[] array) { ((Slar)model).setFreeArgs(array); }

    public void buildSecond(double[] array) { ((Slar)model).setFreeArgs(array); }

    public Model getModel() {
        return model;
    }
}
