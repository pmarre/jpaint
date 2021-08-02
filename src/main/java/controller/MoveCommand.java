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
       this.pc = shapeInfo.pc;
    }

    public static void moveShape() {
        // Calculate the distance moved
        double x_move = end.getX() - start.getX();
        double y_move = end.getY() - start.getY();

        for (CreateShapeCommand s : selectedShapes.getShapes()) {
            ShapeInfo si = s.shapeInfo;
            Point ns = new Point ((int)(si.start.getX() + x_move), (int)(si.start.getY() + y_move));
            Point ne = new Point ((int)(si.end.getX() + x_move), (int)(si.end.getY() + y_move));
            ShapeInfo nsi = new ShapeInfo(si.state, ns, ne , ns.getX(), ns.getY(), si.width, si.height);
            CreateShapeCommand shape = new CreateShapeCommand(si.state, si.pc, ns, ne, si.sl, nsi);
            shape.execute();
            sl.replaceShape(s, shape);

        }
    }

    @Override
    public void execute() {
        moveShape();
    }

    @Override
    public void redo() {

    }

    @Override
    public void undo() {

    }
}