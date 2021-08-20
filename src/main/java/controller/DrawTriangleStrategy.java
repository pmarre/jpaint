package controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import model.ShapeInfo;
import model.interfaces.IShape;

public class DrawTriangleStrategy implements IShape {

  int x;
  int y;
  int width;
  int height;

  public void draw(Graphics2D g2d, CreateShapeCommand csc) {
    ShapeInfo si = csc.shapeInfo;
    x = (int) si.x;
    y = (int) si.y;
    width = (int) si.width;
    height = (int) si.height;
    Color primaryColor = si.primaryColor.getColor();
    Color secondaryColor = si.secondaryColor.getColor();

    int[] xPoints = {x, x + width, x};
    int[] yPoints = {y + height, y, y};

    switch (si.shading) {
      case FILLED_IN:
        g2d.setColor(primaryColor);
        g2d.fillPolygon(xPoints, yPoints, 3);
        break;

      case OUTLINE:
        if (si.isSelected) {
          Stroke dash = new BasicStroke(3, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND,
              0, new float[]{15}, 5);
          g2d.setStroke(dash);
        } else {
          g2d.setStroke(new BasicStroke(3));
        }
        g2d.setColor(primaryColor);
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
