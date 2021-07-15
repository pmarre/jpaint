package controller;

import model.ShapeColor;
import model.ShapeList;
import model.interfaces.*;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class DrawRectangle implements IShape {
    IApplicationState app_state;
    PaintCanvasBase paintcanvas;
    double x;
    double y;
    double w;
    double h;
    ShapeList sl;
    ShapeColor color;

    public DrawRectangle(IApplicationState app, PaintCanvasBase p, double x, double y, double w, double h, ShapeList shapes) {
        this.app_state = app;
        this.paintcanvas = p;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.sl = shapes;
        this.color = app.getActivePrimaryColor();
    }

    public void draw(Graphics2D g2d) {
        color = app_state.getActivePrimaryColor();
        Color c = Color.getColor(color.name());
        Graphics2D g = paintcanvas.getGraphics2D();
        int _x = (int) x;
        int _y = (int) y;
        int _w = (int) w;
        int _h = (int) h;
        g2d.setColor(c);
        g2d.fillRect(_x,_y,_w,_h);
    }

}
