package controller;

import model.interfaces.ICommand;

public class RedoCommand implements ICommand {
    @Override
    public void execute() {
        System.out.println("Redo from command");
        CommandHistory.redo();

    }
}