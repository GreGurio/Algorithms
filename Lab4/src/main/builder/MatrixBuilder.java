package com.gmail.gurik.builder;

import com.gmail.gurik.model.Matrix;
import com.gmail.gurik.model.Model;


public class MatrixBuilder extends Builder {

    public void buildModel() {
        model = new Matrix();
    }

    public void buildMatrix(double[][] array) {
        ((Matrix)model).setMatrix(array);
    }

    public void buildMatrix(int[][] array) {
        ((Matrix)model).setMatrix(array);
    }

    protected void buildSecond(int[] array) {}

    protected void buildSecond(double[] array) {}

    public Model getModel() {
        return model;
    }
}
