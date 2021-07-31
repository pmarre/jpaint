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
    static Point location;

    public MoveCommand (Point start, Point end, ShapeList selectedShapes, ShapeList sl, ShapeInfo shapeInfo) {
       this.start = start;
       this.end = end;
       this.selectedShapes = selectedShapes;
       this.sl = sl;
       this.shapeInfo = shapeInfo;
    }

    public static void moveShape() {
        // Calculate the distance moved
        double x_move = start.getX() - end.getX();
        double y_move = start.getY() - end.getY();

        for (CreateShapeCommand s : selectedShapes.getShapes()) {
            double x = s.getStart().getX();
            double y = s.getStart().getY();
            double w = x - s.getEnd().getX();
            double h = y - s.getEnd().getY();

            int new_x = (int) (s.getStart().getX() + x_move);
            int new_y = (int) (s.getStart().getY() + x_move);

            int new_e = (int) (s.getEnd().getX() + x_move);
            int new_ey = (int) (s.getEnd().getY() + y_move);

            Point x1 = new Point(new_x, new_y);
            Point x2 = new Point(new_e, new_ey);

            Point[] xy = s.getXY();
            int deltaX =  (int) Math.abs(xy[0].getX() - start.getX());
            int deltaY = (int) Math.abs(xy[0].getY() - start.getY());

            xy[0].translate(deltaX, deltaY);
            xy[1].translate(deltaX, deltaY);

            CreateShapeCommand shape = new CreateShapeCommand(state, pc, x1, x2, sl, shapeInfo);

            int i = sl.getShapes().indexOf(s);
            sl.getShapes().set(i, shape);

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