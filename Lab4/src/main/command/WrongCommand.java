package com.gmail.gurik.command;

import com.gmail.gurik.view.View;

public class WrongCommand implements Command {
    View reciever;

    public WrongCommand(View view) {
        this.reciever = view;
    }

    public void execute() {
        reciever.wrong();
    }
}
