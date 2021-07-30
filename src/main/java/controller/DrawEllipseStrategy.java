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

        @Override
public void draw(Graphics2D g2d, CreateShapeCommand csc) {
        int _x = (int) x;
        int _y = (int) y;
        int _w = (int) w;
        int _h = (int) h;
        Color primaryColor = app_state.getActivePrimaryColor().getColor();
        Color secondaryColor = app_state.getActiveSecondaryColor().getColor();
        switch(app_state.getActiveShapeShadingType()) {
                case FILLED_IN:
                        g2d.setColor(primaryColor);
                        g2d.fillOval(_x,_y,_w,_h);
                        break;
                case OUTLINE:
                        g2d.setStroke(new BasicStroke(3));
                        g2d.setColor(secondaryColor);
                        g2d.drawOval(_x,_y,_w,_h);
                        break;
                case OUTLINE_AND_FILLED_IN:
                        g2d.setColor(primaryColor);
                        g2d.fillOval(_x,_y,_w,_h);
                        g2d.setStroke(new BasicStroke(3));
                        g2d.setColor(secondaryColor);
                        g2d.drawOval(_x,_y,_w,_h);
                        break;
                default:
                        throw new IllegalArgumentException("Please select a valid option.");
        }


        }
}
