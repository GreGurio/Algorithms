package com.gmail.gurik.builder;

import com.gmail.gurik.model.Model;


public abstract class Builder {

    Model model;

    abstract void buildModel();

    abstract void buildMatrix(double[][] array);

    abstract void buildMatrix(int[][] array);

    abstract void buildSecond(int[] array);

    abstract void buildSecond(double[] array);

    public Model getModel() {
        return model;
    }
}
