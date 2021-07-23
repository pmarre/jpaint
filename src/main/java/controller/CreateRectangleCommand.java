package controller;

import model.ShapeInfo;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;
import model.ShapeColor;

import java.awt.*;

public class CreateRectangleCommand implements IUndoable, ICommand {
    public ShapeInfo si;
    public PaintCanvasBase pc;
    public Point start;
    public Point end;
    static double width, height, x, y;

    public CreateRectangleCommand(ShapeInfo si) {
        CommandHistory.add(this);
        this.si = si;
        this.pc = si.pc;
        this.start = si.start;
        this.end = si.end;
    }


    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }


    @Override
    public void execute() {

    }
}


