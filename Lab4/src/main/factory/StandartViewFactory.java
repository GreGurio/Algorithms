package com.gmail.gurik.factory;

import com.gmail.gurik.view.StandartView;
import com.gmail.gurik.view.View;


public class StandartViewFactory implements ViewFactory {

    public View getView() {
        return new StandartView();
    }

}
