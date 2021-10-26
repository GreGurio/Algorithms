package com.gmail.gurik.factory;

import com.gmail.gurik.view.SlarView;
import com.gmail.gurik.view.View;


public class SlarViewFactory implements ViewFactory {

    public View getView() {
        return new SlarView();
    }

}
