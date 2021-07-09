package controller;

import model.ShapeType;
import model.ShapeList;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.lang.*;
import java.util.ArrayList;

public class CreateShapeCommand implements ICommand, IUndoable {
    ApplicationState appState;
    PaintCanvasBase pc;
    Point start;
    Point end;
    ShapeList shapelist;
    ArrayList<CreateShapeCommand> sl;


    public CreateShapeCommand( ApplicationState appState, PaintCanvasBase pc, Point start, Point end, ShapeList sl) {
        this.appState = appState;
        this.pc = pc;
        this.start = start;
        this.end = end;
        this.shapelist = sl;
    }


    public void execute() {
        // sl = shapelist.getShapes();

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

            switch (curr_shape) {
                case ELLIPSE:
                    IShape el = new DrawEllipse(appState, pc, x, y, width, height);
                    el.draw(g);
                    break;

                case RECTANGLE:
                    IShape rec = new DrawRectangle(appState, pc, x, y, width, height, shapelist);
                    rec.draw(g);
                    break;

                case TRIANGLE:
                    System.out.println("drawing triangle"); // Need to look up shape
                    break;

                default:
                    throw new IllegalStateException("No shape selected");
            }
            System.out.println("drawing a " + appState.getActiveShapeType());

    }




    public void undo() {
        System.out.println("undo");
    }

    public void redo() {
        System.out.println("redo");
    }
}
