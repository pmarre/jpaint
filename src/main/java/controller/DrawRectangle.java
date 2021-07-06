package controller;

import main.Main;
import model.interfaces.*;
import view.gui.*;
import model.persistence.*;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.awt.event.MouseListener;
import view.interfaces.PaintCanvasBase;


public class DrawRectangle implements IShape {
    IApplicationState app_state;
    PaintCanvasBase paintcanvas;
    Point start;
    Point end;

    public DrawRectangle(IApplicationState app, PaintCanvasBase p, Point start, Point end) {
        this.app_state = app;
        this.paintcanvas = p;
        this.start = start;
        this.end = end;
    }

//    public int[] getStartXY(int x, int y){
//        int[] xy = {x, y};
//        return xy;
//    }
//
//    public int[] getEndXY(int x, int y){
//        int[] xy = {x, y};
//        return xy;
//    }

    public void draw(PaintCanvasBase g, double[] start, double[] end) {
        double width, startx;
        double height, starty;

        // set width and starting X point
        if (end[0] > start[0]) {
            width = end[0] - start[0];
            startx = start[0];
        } else {
            width = start[0] - end[0];
            startx = end[0];
        }

        // set height and starting Y point
        if (end[1] > start[1]) {
            height = end[1] - start[1];
            starty = start[1];
        } else {
            height = start[1] - end[1];
            starty = end[1];
        }


      Graphics2D pc = g.getGraphics2D();
        int x = (int) startx;
        int y = (int) starty;
        int w = (int) width;
        int h = (int) height;
        pc.fillRect(x,y,w,h);

    }
}
