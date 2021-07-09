package controller;

import model.interfaces.IShape;
import model.interfaces.*;
import view.interfaces.*;
import java.awt.Graphics2D;

public class DrawEllipse implements IShape {
IApplicationState app_state;
        PaintCanvasBase paintcanvas;
        double x;
        double y;
        double w;
        double h;

public DrawEllipse(IApplicationState app, PaintCanvasBase p, double x, double y, double w, double h) {
        this.app_state = app;
        this.paintcanvas = p;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        }

public void draw(Graphics2D g2d) {
        Graphics2D g = paintcanvas.getGraphics2D();
        int _x = (int) x;
        int _y = (int) y;
        int _w = (int) w;
        int _h = (int) h;
        g.fillOval(_x,_y,_w,_h);

        }
}
