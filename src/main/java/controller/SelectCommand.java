package controller;

import java.awt.Point;
import java.util.Iterator;
import model.ListContainer;
import model.ShapeCollection;
import model.ShapeColor;
import model.ShapeInfo;
import model.ShapeShadingType;
import model.interfaces.ICommand;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

public class SelectCommand implements ICommand {

  static ShapeCollection shapeCollection;
  static ShapeCollection selected;
  static ShapeCollection tempList = new ShapeCollection();
  Point start;
  Point end;
  PaintCanvasBase pc;
  ApplicationState state;

  public SelectCommand(Point start, Point end, PaintCanvasBase pc, ApplicationState state) {
    this.start = start;
    this.end = end;
    this.pc = pc;
    this.state = state;
  }


  @Override
  public void execute() {
    selected = ListContainer.getSelectedShapes();
    ListContainer.getSelectedShapes().getShapes().clear();
    shapeCollection = ListContainer.getShapeList();
    tempList = new ShapeCollection();
    CreateShapeCommand outline_shape;
    Iterator shape_iter = shapeCollection.getShapes().iterator();

    while (shape_iter.hasNext()) {
      CreateShapeCommand shape = (CreateShapeCommand) shape_iter.next();

      if (shape.getEnd().getX() > start.getX() &&
          shape.getStart().getX() < end.getX() &&
          shape.getEnd().getY() > start.getY() &&
          shape.getStart().getY() < end.getY()) {

        if (shape.shapeInfo.isSelected) {
          shape_iter.remove();
        }

        if (shape.shapeInfo.inGroup) {
          outline_shape = shape.shapeInfo.outlineShape;
          for (ShapeCollection sc : ListContainer.getGroupCollection()) {
            if (sc.getShapes().contains(shape)) {
              // N^2 performance, needs improvement
              for (CreateShapeCommand s : sc.getShapes()) {
                selected.addShape(s);
              }
              break; // leave loop once group is found
            }
          }
        } else {
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
          System.out.println("here");
          shapeCollection.removeShape(s);
          s.shapeInfo.pc.repaint();
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
