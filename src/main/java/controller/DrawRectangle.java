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

    public DrawRectangle(IApplicationState app, PaintCanvasBase p) {
        this.app_state = app;
        this.paintcanvas = p;
    }

    public int[] getStartXY(int x, int y){
        int[] xy = {x, y};
        return xy;
    }

    public int[] getEndXY(int x, int y){
        int[] xy = {x, y};
        return xy;
    }

    public void draw(Graphics2D g, int[] start, int[] end) {
        int width, startx;
        int height, starty;

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

        g.fillRect(startx,starty,width,height);
        System.out.println(app_state.getActiveMouseMode());
    }
}
