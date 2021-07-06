package controller;

import model.MouseMode;
import model.interfaces.IShape;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MouseHandler extends MouseAdapter {
    ApplicationState appState;
    PaintCanvasBase paintCanvas;
    public MouseHandler(ApplicationState appState, PaintCanvasBase paintCanvas) {
        this.appState = appState;
        this.paintCanvas = paintCanvas;
    }



    Point start, end;
    public void mousePressed(MouseEvent event) {
        start = event.getPoint();
        System.out.println(start);
    }

    public void mouseReleased(MouseEvent event) {
        end = event.getPoint();
        if (appState.getActiveMouseMode() == MouseMode.DRAW) {
            IShape shape = new DrawRectangle(appState, paintCanvas, start, end);
            double[] start1 = {start.getX(), start.getY()};
            double[] end1 = {end.getX(), end.getY()};
            shape.draw(paintCanvas, start1, end1);
        }

    }




}
