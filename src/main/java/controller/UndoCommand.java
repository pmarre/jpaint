package controller;

import model.interfaces.ICommand;

public class UndoCommand implements ICommand {

    @Override
    public void execute() {
        System.out.println("Undo from command");
        CommandHistory.undo();
    }
}
