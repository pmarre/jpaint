package controller;

import model.GraphicsSingleton;
import model.ListContainer;
import model.ShapeColor;
import model.ShapeInfo;
import model.ShapeCollection;
import model.ShapeShadingType;
import model.interfaces.ICommand;
import model.persistence.ApplicationState;

import java.awt.*;

import view.interfaces.PaintCanvasBase;

public class SelectCommand implements ICommand {

  Point start;
  Point end;
  PaintCanvasBase pc;
  ApplicationState state;
  static ShapeCollection shapeCollection;
  static ShapeCollection selected;
  static ShapeCollection tempList = new ShapeCollection();

  public SelectCommand(Point start, Point end, PaintCanvasBase pc, ApplicationState state) {
    this.start = start;
    this.end = end;
    this.pc = pc;
    this.state = state;
  }


  @Override
  public void execute() {
    selected = ListContainer.getSelectedShapes();
    selected.getShapes().clear();
    shapeCollection = ListContainer.getShapeList();
    tempList = new ShapeCollection();
    CreateShapeCommand outline_shape;
    for (CreateShapeCommand shape : shapeCollection.getShapes()) {

      if (shape.getEnd().getX() > start.getX() &&
          shape.getStart().getX() < end.getX() &&
          shape.getEnd().getY() > start.getY() &&
          shape.getStart().getY() < end.getY()) {

        if (shape.shapeInfo.inGroup) {
          outline_shape = shape.shapeInfo.outlineShape;
          System.out.println("in group");
          for (ShapeCollection sc : ListContainer.getGroupCollection()) {
//            System.out.println("loop shape");
            if (sc.getShapes().contains(shape)) {
              for (CreateShapeCommand s : sc.getShapes()) {
                selected.addShape(s);
              }
              break;
//              selected.addShape(shape);
//              selected.addShape(outline_shape);
//              tempList.addShape(outline_shape);
            }
          }
        }

        else {
          System.out.println("not in group");
          outline_shape = outlineSelected(shape);
          shape.shapeInfo.outlineShape = outline_shape;
          selected.addShape(shape);
        }


        selected.addShape(outline_shape);
        tempList.addShape(outline_shape);
      }


    }

    for (CreateShapeCommand cs : tempList.getShapes()) {
      shapeCollection.addShape(cs);
    }

// Hacked together way to remove outline
    if (selected.getShapes().size() == 0) {
      int count = 0;
      int size = shapeCollection.getShapes().size();
      while (count < size) {
        CreateShapeCommand s = shapeCollection.getShapes().get(count);
        if (s.shapeInfo.isSelected) {
          shapeCollection.removeShape(s);
          size--;
        } else {
          count++;
        }
      }
    }

    tempList.getShapes().clear();
  }

  public CreateShapeCommand outlineSelected(CreateShapeCommand shape) {
    ShapeInfo si = new ShapeInfo(shape.shapeInfo.state, shape.shapeInfo.pc, shape.shapeInfo.start,
        shape.shapeInfo.end, shape.shapeInfo.x - 5, shape.shapeInfo.y - 5,
        shape.shapeInfo.width + 10,
        shape.shapeInfo.height + 10);
    Point ns = new Point((int) si.start.getX() - 5, (int) si.start.getY() - 5);
    Point ne = new Point((int) si.end.getX() - 5, (int) si.end.getY() - 5);
    si.start = ns;
    si.end = ne;
    si.shape = shape.shapeInfo.shape;
    si.primaryColor = ShapeColor.LIGHT_GRAY;
    si.shading = ShapeShadingType.OUTLINE;
    si.isSelected = true;
    CreateShapeCommand outline = new CreateShapeCommand(si.pc, si.start, si.end, si);
    shape.shapeInfo.pc.repaint();
    System.out.println("border");
    return outline;

  }


}
