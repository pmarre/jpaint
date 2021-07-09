package controller;

import model.MouseMode;
import model.persistence.ApplicationState;
import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;
import model.ShapeList;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MouseHandler extends MouseAdapter {
    ApplicationState appState;
    PaintCanvasBase paintCanvas;
    Graphics2D g;
    ShapeList shapeList;
    ArrayList<CreateShapeCommand> sl;

    public MouseHandler(ApplicationState appState, PaintCanvasBase paintCanvas, Graphics2D g, ShapeList sl) {
        shapeList = new ShapeList();
        this.appState = appState;
        this.paintCanvas = paintCanvas;
        this.g = g;
        this.shapeList = sl;
    }

    Point start, end;
    public void mousePressed(MouseEvent event) {
        start = event.getPoint();
        System.out.println(start);
    }

    public void mouseReleased(MouseEvent event) {
        end = event.getPoint();
        PaintCanvasBase pc = new PaintCanvas();
        MouseMode MM = appState.getActiveMouseMode();

        switch (MM) {
            case DRAW:
                CreateShapeCommand csc = new CreateShapeCommand(appState, paintCanvas, start, end, shapeList);
                shapeList.addShape(csc);
                csc.execute();
                break;

            case MOVE:
                System.out.println("Mouse in move mode");
                break;

            case SELECT:
                System.out.println("Mouse in select mode");
                break;

            default:
                throw new IllegalStateException("No mouse selected");
        }

    }




}
