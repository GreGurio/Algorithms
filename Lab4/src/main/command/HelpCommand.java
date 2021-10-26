package com.gmail.gurik.command;

import com.gmail.gurik.view.View;

public class HelpCommand implements Command {
    View reciever;

    public HelpCommand(View view) {
        this.reciever = view;
    }

    public void execute() {
        reciever.help();
    }
}
