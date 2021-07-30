package controller;

import model.ShapeList;
import model.interfaces.ICommand;
import model.interfaces.IObserver;
import model.interfaces.IShape;

import java.awt.*;

import model.persistence.ApplicationState;
import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;
import model.ShapeInfo;

public class DrawShapeCommand implements IObserver {
    static Graphics2D g2d;
    //static IShape shape;
    static ApplicationState state;
    static Point start, end;
    static PaintCanvasBase pc;
    static IShape shape;
    static ShapeList shapeList;

    public DrawShapeCommand(ApplicationState state, PaintCanvasBase pc,  Point start, Point end, ShapeList shapeList) {
        this.state = state;
        this.start = start;
        this.end = end;
        this.pc = pc;
        this.g2d = (Graphics2D) pc.getGraphics();
        this.shapeList = shapeList;
    }


    public static void DrawStrategy(CreateShapeCommand csc, ShapeInfo si) {

        switch (si.shape) {
            case ELLIPSE:
                shape = new DrawEllipseStrategy();
                break;
            case RECTANGLE:
                shape = new DrawRectangleStrategy();
                break;
            case TRIANGLE:
                shape = new DrawTriangleStrategy();
                break;
            default:
                throw new IllegalArgumentException("Add shape");
        }
        shape.draw(g2d, csc);


    }



    @Override
    public void update() {
        //pc.repaint();
        for (CreateShapeCommand s : shapeList.getShapes()) {
            DrawStrategy(s, s.shapeInfo);
        }
    }
}
