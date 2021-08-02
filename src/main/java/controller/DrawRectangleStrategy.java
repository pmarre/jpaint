package controller;

import model.ShapeColor;
import model.ShapeInfo;
import model.ShapeList;
import model.interfaces.*;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class DrawRectangleStrategy implements IShape {
    IApplicationState app_state;
    PaintCanvasBase paintcanvas;
    int x;
    int y;
    int w;
    int h;
    ShapeList sl;


    public void draw(Graphics2D g2d, CreateShapeCommand csc) {
        ShapeInfo si = csc.shapeInfo;
        x = (int) si.x;
        y = (int) si.y;
        w = (int) si.width;
        h = (int) si.height;
        Color primaryColor = si.primaryColor.getColor();
        Color secondaryColor = si.secondaryColor.getColor();

        switch (si.shading) {
            case FILLED_IN:
                g2d.setColor(primaryColor);
                g2d.fillRect(x, y, w, h);
                break;
            case OUTLINE:
                g2d.setStroke(new BasicStroke(3));
                g2d.setColor(secondaryColor);
                g2d.drawRect(x, y, w, h);
                break;
            case OUTLINE_AND_FILLED_IN:
                g2d.setColor(primaryColor);
                g2d.fillRect(x, y, w, h);
                g2d.setStroke(new BasicStroke(3));
                g2d.setColor(secondaryColor);
                g2d.drawRect(x, y, w, h);
                break;
        }
    }
}
