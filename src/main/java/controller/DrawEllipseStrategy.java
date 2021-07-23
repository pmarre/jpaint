package controller;

import model.ShapeColor;
import model.interfaces.IShape;
import model.interfaces.*;
import view.interfaces.*;

import java.awt.*;

public class DrawEllipseStrategy implements IShape {
IApplicationState app_state;
        PaintCanvasBase paintcanvas;
        double x;
        double y;
        double w;
        double h;

public DrawEllipseStrategy(IApplicationState app, PaintCanvasBase p, double x, double y, double w, double h) {
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
        Color primaryColor = app_state.getActivePrimaryColor().getColor();
        Color secondaryColor = app_state.getActiveSecondaryColor().getColor();
        switch(app_state.getActiveShapeShadingType()) {
                case FILLED_IN:
                        g.setColor(primaryColor);
                        g.fillOval(_x,_y,_w,_h);
                        break;
                case OUTLINE:
                        g.setStroke(new BasicStroke(3));
                        g.setColor(secondaryColor);
                        g.drawOval(_x,_y,_w,_h);
                        break;
                case OUTLINE_AND_FILLED_IN:
                        g.setColor(primaryColor);
                        g.fillOval(_x,_y,_w,_h);
                        g.setStroke(new BasicStroke(3));
                        g.setColor(secondaryColor);
                        g.drawOval(_x,_y,_w,_h);
                        break;
                default:
                        throw new IllegalArgumentException("Please select a valid option.");
        }


        }
}
