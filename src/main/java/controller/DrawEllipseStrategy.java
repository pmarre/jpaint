package controller;

import model.ShapeInfo;
import model.interfaces.IShape;

import java.awt.*;

public class DrawEllipseStrategy implements IShape {

  int x;
  int y;
  int w;
  int h;

  @Override
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
        g2d.fillOval(x, y, w, h);
        break;

      case OUTLINE:
        if (si.isSelected) {
          Stroke dash = new BasicStroke(3, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL,
              0, new float[]{10}, 0);
          g2d.setStroke(dash);
        } else {
          g2d.setStroke(new BasicStroke(3));
        }
        g2d.setColor(primaryColor);
        g2d.drawOval(x, y, w, h);
        break;

      case OUTLINE_AND_FILLED_IN:
        g2d.setColor(primaryColor);
        g2d.fillOval(x, y, w, h);
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(secondaryColor);
        g2d.drawOval(x, y, w, h);
        break;

      default:
        throw new IllegalArgumentException("Please select a valid option.");
    }


  }
}
