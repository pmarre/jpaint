package controller;

import model.ShapeColor;
import model.ShapeList;
import model.interfaces.*;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class DrawRectangleStrategy implements IShape {
    IApplicationState app_state;
    PaintCanvasBase paintcanvas;
    double x;
    double y;
    double w;
    double h;
    ShapeList sl;
    ShapeColor color;

    public DrawRectangleStrategy(IApplicationState app, PaintCanvasBase p, double x, double y, double w, double h, ShapeList shapes) {
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
        int _x = (int) x;
        int _y = (int) y;
        int _w = (int) w;
        int _h = (int) h;
        Color primaryColor = app_state.getActivePrimaryColor().getColor();
        Color secondaryColor = app_state.getActiveSecondaryColor().getColor();
        switch (app_state.getActiveShapeShadingType()) {
            case FILLED_IN:
                g2d.setColor(primaryColor);
                g2d.fillRect(_x, _y, _w, _h);
                break;
            case OUTLINE:
                g2d.setStroke(new BasicStroke(3));
                g2d.setColor(secondaryColor);
                g2d.drawRect(_x, _y, _w, _h);
                break;
            case OUTLINE_AND_FILLED_IN:
                g2d.setColor(primaryColor);
                g2d.fillRect(_x, _y, _w, _h);
                g2d.setStroke(new BasicStroke(3));
                g2d.setColor(secondaryColor);
                g2d.drawRect(_x, _y, _w, _h);
                break;
        }
    }
}
