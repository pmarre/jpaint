package controller;

import model.GraphicsSingleton;
import model.ListContainer;
import model.ShapeCollection;
import model.interfaces.IShape;

import java.awt.*;

import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

public class DrawShapeCommand {

  static Graphics2D g2d;
  static ApplicationState state;
  static Point start, end;
  static PaintCanvasBase pc;
  static IShape shape;
  static ShapeCollection shapeCollection;

  public DrawShapeCommand(ApplicationState state, PaintCanvasBase pc, Point start, Point end) {
    this.state = state;
    this.start = start;
    this.end = end;
    this.pc = pc;
    this.shapeCollection = ListContainer.getShapeList();
  }


  public static void DrawStrategy(CreateShapeCommand cs) {

    switch (cs.shapeInfo.shape) {
      case ELLIPSE:
        shape = new DrawEllipseStrategy();
        break;
      case RECTANGLE:
        shape = new DrawRectangleStrategy();
        break;
      case TRIANGLE:
        shape = new DrawTriangleStrategy();
        break;
      default:
        throw new IllegalArgumentException("Add shape");
    }

    g2d = GraphicsSingleton.getInstance().getG2D();
    shape.draw(g2d, cs);

  }
}
