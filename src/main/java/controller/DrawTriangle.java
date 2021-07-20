package controller;

import model.ShapeColor;
import model.interfaces.IShape;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;
import model.ShapeList;

import java.awt.*;

public class DrawTriangle implements IShape {
    ApplicationState appState;
    PaintCanvasBase pc;
    int x;
    int y;
    int width;
    int height;
    ShapeList shapelist;

    public DrawTriangle(ApplicationState appState, PaintCanvasBase pc, double x, double y, double width, double height, ShapeList shapelist) {
        this.appState = appState;
        this.pc = pc;
        this.x = (int) x;
        this.y = (int) y;
        this.width = (int) width;
        this.height = (int) height;
        this.shapelist = shapelist;
    }




    public void draw(Graphics2D g2d){
        int[] xPoints = {x, x+width, x};
        int[] yPoints = {y+height, y, y};
        Color primaryColor = appState.getActivePrimaryColor().getColor();
        Color secondaryColor = appState.getActiveSecondaryColor().getColor();

        switch (appState.getActiveShapeShadingType()) {
            case FILLED_IN:
                g2d.setColor(primaryColor);
                g2d.fillPolygon(xPoints, yPoints, 3);
                break;
            case OUTLINE:
                g2d.setStroke(new BasicStroke(3));
                g2d.setColor(secondaryColor);
                g2d.drawPolygon(xPoints, yPoints, 3);
                break;
            case OUTLINE_AND_FILLED_IN:
                g2d.setColor(primaryColor);
                g2d.fillPolygon(xPoints, yPoints, 3);
                g2d.setStroke(new BasicStroke(3));
                g2d.setColor(secondaryColor);
                g2d.drawPolygon(xPoints, yPoints, 3);
                break;
        }
    }
}
