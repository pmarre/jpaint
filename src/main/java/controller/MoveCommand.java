package controller;

import model.ShapeInfo;
import model.ShapeList;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.interfaces.IShape;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class MoveCommand implements ICommand, IUndoable {
    static Point start;
    static Point end;
    static ShapeInfo shapeInfo;
    static ShapeList sl;
    static ShapeList selectedShapes;
    static PaintCanvasBase pc;
    static ApplicationState state;

    public MoveCommand (ShapeList selected, Point start, Point end, ShapeInfo shapeInfo, ShapeList sl, PaintCanvasBase pc, ApplicationState state) {
        this.selectedShapes = selected;
        this.start = start;
        this.end = end;
        this.shapeInfo = shapeInfo;
        this.sl = sl;
        this.pc = pc;
        this.state = state;
    }

    public static void moveShape() {
        for (CreateShapeCommand s : selectedShapes.getShapes()) {
            double x = s.getStart().getX();
            double y = s.getStart().getY();
            double w = x - s.getEnd().getX();
            double h = y - s.getEnd().getY();


            Point[] xy = s.getXY();
            int deltaX =  (int) Math.abs(xy[0].getX() - start.getX());
            int deltaY = (int) Math.abs(xy[0].getY() - start.getY());

            xy[0].translate(deltaX, deltaY);
            xy[1].translate(deltaX, deltaY);

            CreateShapeCommand shape = new CreateShapeCommand(state, pc, xy[0], xy[1], sl);

            sl.removeShape(s);
            sl.addShape(shape);
            shape.execute();
        }
    }


    public void execute() {
        moveShape();
        System.out.println("in move mode execute");
    }

    @Override
    public void redo() {

    }

    @Override
    public void undo() {

    }
}
