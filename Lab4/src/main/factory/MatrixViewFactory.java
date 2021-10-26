package com.gmail.gurik.factory;

import com.gmail.gurik.view.MatrixView;
import com.gmail.gurik.view.View;


public class MatrixViewFactory implements ViewFactory{

    public View getView() {
        return new MatrixView();
    }

}
