package controller;

import model.ListContainer;
import model.MouseMode;
import model.ShapeCollection;
import model.ShapeInfo;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MouseHandler extends MouseAdapter {

  ApplicationState appState;
  PaintCanvasBase paintCanvas;
  Graphics2D g;
  ShapeCollection shapeCollection;
  ArrayList<CreateShapeCommand> sl;
  DrawShapeCommand cmd;
  ShapeCollection selected;

  public MouseHandler(ApplicationState appState, PaintCanvasBase paintCanvas) {
    shapeCollection = ListContainer.getShapeList();
    selected = new ShapeCollection();
    this.appState = appState;
    this.paintCanvas = paintCanvas;
//        this.g = g;
//        this.shapeCollection = sl;
//        this.selected = selected;
  }

  Point start, end;

  public void mousePressed(MouseEvent event) {
    start = event.getPoint();
    System.out.println(start);
  }

  public void mouseReleased(MouseEvent event) {
    end = event.getPoint();
    MouseMode MM = appState.getActiveMouseMode();
    // TEMP CODE:
    double[] start1 = {start.getX(), start.getY()};
    double[] end1 = {end.getX(), end.getY()};
    double width, height, x, y;

    // Switch x's if user drags from r->l
    if (end1[0] > start1[0]) {
      width = end1[0] - start1[0];
      x = start1[0];
    } else {
      width = start1[0] - end1[0];
      x = end1[0];
    }

    // Switch y's if user drags from r->l
    if (end1[1] > start1[1]) {
      height = end1[1] - start1[1];
      y = start1[1];
    } else {
      height = start1[1] - end1[1];
      y = end1[1];
    }

    // END TEMP CODE
    ShapeInfo shapeInfo = new ShapeInfo(appState, paintCanvas, start, end, x, y, width, height);
    CreateShapeCommand csc = null;
    if (height < 1 && width < 1) {
      return;
    }
    switch (MM) {
      case DRAW:

        csc = new CreateShapeCommand(paintCanvas, start, end, shapeCollection, shapeInfo);

        shapeCollection.registerObserver(csc);
        shapeCollection.addShape(csc);
        csc.execute();
        paintCanvas.repaint();
        //CommandHistory.add(csc);
        break;

      case MOVE:
        System.out.println(ListContainer.getSelectedShapes().getShapes().size());
        MoveCommand m = new MoveCommand(start, end);
        m.execute();
        paintCanvas.repaint();
        System.out.println("Mouse in move mode");
        break;

      case SELECT:
        SelectCommand c = new SelectCommand(start, end, paintCanvas, appState);
        c.execute();
        System.out.println("Mouse in select mode");
        break;

      default:
        throw new IllegalStateException("No mouse selected");
    }


  }
}