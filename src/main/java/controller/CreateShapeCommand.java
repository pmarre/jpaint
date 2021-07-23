package controller;

import model.ShapeInfo;
import model.ShapeType;
import model.ShapeList;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.lang.*;
import java.util.*;

public class CreateShapeCommand implements ICommand, IUndoable {
    ApplicationState appState;
    PaintCanvasBase pc;
    Point start;
    Point end;
    ShapeList shapelist;
    ArrayList<CreateShapeCommand> sl;
    Stack<CreateShapeCommand> undoStack = new Stack<CreateShapeCommand>();
    Stack<CreateShapeCommand> redoStack = new Stack<CreateShapeCommand>();
    CreateShapeCommand csc;
    ShapeInfo shapeInfo;


    public CreateShapeCommand( ApplicationState appState, PaintCanvasBase pc, Point start, Point end, ShapeList sl, ShapeInfo si) {
        this.appState = appState;
        this.pc = pc;
        this.start = start;
        this.end = end;
        this.shapelist = sl;
        this.shapeInfo = si;
    }


    public void update() {
        //pc.repaint();
        for (CreateShapeCommand shape : shapelist.getShapes()) {
            shape.execute();
        }
    }

    public Point getStart() {
        return start.getLocation();
    }

    public Point getEnd() {
        return end.getLocation();
    }



    public void execute() {
            CommandHistory.add(this);

            ShapeType curr_shape = appState.getActiveShapeType();
            Graphics2D g = pc.getGraphics2D();
            double[] start1 = {start.getX(), start.getY()};
            double[] end1 = {end.getX(), end.getY()};
            double width, height, x, y;

            System.out.println("S_X: " + start1[0] + " S_Y: " + start1[0] + " E_X: " + end1[0] + " E_Y: " + end1[1]);

            // Switch x's if user drags from r->l
            if (end1[0] > start1[0]) {
                width = end1[0] - start1[0];
                x = start1[0];
            } else {
                width = start1[0] - end1[0];
                x = end1[0];
            }

            // Switch y's if user drags from r->l
            if (end1[1] > start1[1]) {
                height = end1[1] - start1[1];
                y = start1[1];
            } else {
                height = start1[1] - end1[1];
                y = end1[1];
            }
            IShape shape;
            switch (curr_shape) {
                case ELLIPSE:
                    shape = new DrawEllipseStrategy(appState, pc, x, y, width, height);
                    break;

                case RECTANGLE:
                    shape = new DrawRectangleStrategy(appState, pc, x, y, width, height, shapelist);
                    break;

                case TRIANGLE:
                    shape = new DrawTriangleStrategy(appState, pc, x, y, width, height, shapelist);
                    break;

                default:
                    throw new IllegalStateException("No shape selected");
            }

            shape.draw(g);
            System.out.println("drawing a " + appState.getActiveShapeType());

    }


    public Point[] getXY() {
        Point[] xy = {start, end};
        return xy;
    }

    @Override
    public void undo() {
        System.out.println("undo");
        sl = shapelist.getShapes();
        if (sl.size() == 0) return;
        pc.repaint();
        if (sl.size() > 0) {
           CreateShapeCommand c = sl.get(sl.size()-1);
           shapelist.removeShape(c);
           redoStack.push(c);
           c.update();
        }
    }

    @Override
    public void redo() {
        System.out.println("redo");
        if (redoStack.size() == 0) return;
        CreateShapeCommand c = redoStack.pop();
        shapelist.addShape(c);
        c.update();
        pc.repaint();
    }
}
