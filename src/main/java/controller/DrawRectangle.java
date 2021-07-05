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
        int width = end[0] - start[0];
        int height = end[1] - start[1];

        //g.drawRect(rec[0],rec[1],rec[2],rec[3]);
        g.fillRect(start[0],start[1],width,height);
        System.out.println(app_state.getActiveMouseMode());
    }
}
