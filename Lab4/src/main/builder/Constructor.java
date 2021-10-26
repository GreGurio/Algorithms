package com.gmail.gurik.builder;

import com.gmail.gurik.model.Model;


public class Constructor {

    private Builder builder;

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    public Model getModel(int[][] matrix) {
        Model model;

        builder.buildModel();
        builder.buildMatrix(matrix);
        model = builder.getModel();

        return model;
    }

    public Model getModel(int[][] matrix, int[] freeArgs) {
        Model model;

        builder.buildModel();
        builder.buildMatrix(matrix);
        builder.buildSecond(freeArgs);
        model = builder.getModel();

        return model;
    }

    public Model getModel(double[][] matrix) {
        Model model;
        builder.buildModel();
        builder.buildMatrix(matrix);
        model = builder.getModel();
        return model;
    }

    public Model getModel(double[][] matrix, double[] freeArgs) {
        Model model;

        builder.buildModel();
        builder.buildMatrix(matrix);
        builder.buildSecond(freeArgs);
        model = builder.getModel();

        return model;
    }
}
