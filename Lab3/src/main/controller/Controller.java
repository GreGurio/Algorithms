package com.gmail.gurik.controller;

import static com.gmail.gurik.services.ShowService.*;
import static com.gmail.gurik.services.ActionService.*;

import com.gmail.gurik.model.Matrix;
import com.gmail.gurik.model.SLAR;
import com.gmail.gurik.sources.Messages;
import com.gmail.gurik.view.MatrixView;
import com.gmail.gurik.view.SLARview;

import java.util.Scanner;


public class Controller<V, M, S> {
    public final V view;
    public final M model;
    public final S service;



    public Controller(V view, M model, S service) {
        this.model = model;
        this.view = view;
        this.service = service;
    }

    public V getView() {
        return view;
    }

    public M getModel() {
        return model;
    }

    public S getService() {
        return service;
    }

}
