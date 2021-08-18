package controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import model.ListContainer;
import model.ShapeCollection;
import model.ShapeColor;
import model.ShapeInfo;
import model.ShapeShadingType;
import model.interfaces.ICommand;

public class GroupCommand implements ICommand {
  ShapeCollection selectedShapes;
  ShapeCollection tempList = new ShapeCollection();
  ArrayList<ShapeCollection> groups;
  Point[] points;
  double max_x;
  double max_y;
  double min_x;
  double min_y ;
  CreateShapeCommand border;
  ArrayList<ShapeCollection> temp = new ArrayList<ShapeCollection>();
  ShapeCollection g;


  @Override
  public void execute() {
    selectedShapes = ListContainer.getSelectedShapes();

    // if no shapes selected, return
    if (selectedShapes.getShapes() == null) return;
    // if selected shapes are already in a group, select group border, return
    g = new ShapeCollection();
    points = selectedShapes.getShapes().get(0).getXY();
    max_x = points[0].getX();
    max_y = points[0].getY();
     min_x = points[1].getX();
     min_y = points[1].getY();
    groups = ListContainer.getGroupCollection();
    for (CreateShapeCommand c : selectedShapes.getShapes()) {
      g.addShape(c);
    }
    groups.add(g);
    //selectedShapes.clearShapes();

    for (ShapeCollection group : groups) {

      for (CreateShapeCommand cs : group.getShapes()) {
        points = cs.getXY();
        if (max_x < points[0].getX() && points[0].getX() > points[1].getX())
          max_x = points[0].getX();
        else if (max_x < points[1].getX() && points[1].getX() > points[0].getX())
          max_x = points[1].getX();

        if (max_y < points[0].getY() && points[0].getY() > points[1].getY())
          max_y = points[0].getY();
        else if (max_y < points[1].getY() && points[1].getY() > points[0].getY())
          max_y = points[1].getY();

        if (min_x > points[0].getX() && points[0].getX() < points[1].getX()) {
          min_x = points[0].getX();
        } else if (min_x < points[1].getX() && points[1].getX() < points[0].getX())
          min_x = points[1].getX();

        if (min_y > points[0].getY() && points[0].getY() < points[1].getY())
          min_y = points[0].getY();
        else if (min_y < points[1].getY() && points[1].getY() < points[0].getY())
          min_y = points[1].getY();

        cs.shapeInfo.inGroup = true;



       // cs.shapeInfo.outlineShape = border;
        tempList.addShape(cs);


        cs.update();

      }
      border = outlineGroup(selectedShapes.getShapes().get(0));
      tempList.addShape(border);
      for (CreateShapeCommand c : tempList.getShapes()) {
        c.shapeInfo.outlineShape = border;
        group.addShape(c);

      }
    }
       //border = outlineGroup(selectedShapes.getShapes().get(0));
//      group.addShape(cs);
//      group.addShape(border);




    // add group to groupCollection
    for (ShapeCollection gc : temp) {
      groups.add(gc);
    }

    selectedShapes.clearShapes();
    if (selectedShapes.getShapes().size() == 0) {
      int count = 0;
      int size = ListContainer.getShapeList().getShapes().size();
      while (count < size) {
        CreateShapeCommand s = ListContainer.getShapeList().getShapes().get(count);
        if (s.shapeInfo.isSelected) {
          ListContainer.getShapeList().removeShape(s);
          size--;
        } else {
          count++;
        }
      }

    for (CreateShapeCommand cs : tempList.getShapes()) {
      selectedShapes.addShape(cs);
    }
    }

    ListContainer.getShapeList().addShape(border);
    //ListContainer.getShapeList().addShape(border);

  }

  public CreateShapeCommand outlineGroup(CreateShapeCommand shape) {

  int w = (int) max_x - (int) min_x;
  int h = (int) max_y - (int) min_y;
  ShapeInfo si = new ShapeInfo(shape.shapeInfo.state, shape.shapeInfo.pc, shape.shapeInfo.start,
        shape.shapeInfo.end, min_x, min_y,
        w + 10,
        h + 10);
    Point ne = new Point((int) max_x, (int) min_y);
    Point ns = new Point((int) min_x, (int) max_y);
    si.start = ns;
    si.end = ne;
    si.shape = shape.shapeInfo.shape;
    si.primaryColor = ShapeColor.LIGHT_GRAY;
    si.shading = ShapeShadingType.OUTLINE;
    si.isSelected = true;
    CreateShapeCommand outline = new CreateShapeCommand(si.pc, si.start, si.end, si);
    shape.shapeInfo.pc.repaint();
    return outline;
  }




}
