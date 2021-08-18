package controller;

import java.awt.Point;
import model.ListContainer;
import model.ShapeCollection;
import model.ShapeColor;
import model.ShapeInfo;
import model.ShapeShadingType;
import model.interfaces.ICommand;

public class UngroupCommand implements ICommand {

  @Override
  public void execute() {
    CreateShapeCommand outline;
    ShapeCollection temp = new ShapeCollection();
    int index = 0;
    for (CreateShapeCommand shape : ListContainer.getSelectedShapes().getShapes()) {
      // if shape is in a group, remove
      if (shape.shapeInfo.inGroup) {
        shape.shapeInfo.inGroup = false;
        CreateShapeCommand new_outline = outlineSelected(shape);
        temp.addShape(new_outline);
        for (ShapeCollection sc : ListContainer.getGroupCollection()) {
          if (sc.getShapes().contains(shape)) index = ListContainer.getGroupCollection().indexOf(sc);
        }
      }

      // if shape is a selection border, remove
      if (shape.shapeInfo.isSelected) {
        ListContainer.getShapeList().removeShape(shape);
      }

//      outline = shape.shapeInfo.outlineShape;
//      shape.shapeInfo.inGroup = false;
//      ListContainer.getShapeList().removeShape(outline);


//      shape.shapeInfo.outlineShape = new_outline;
//      ListContainer.getShapeList().addShape(new_outline);
      //ListContainer.getSelectedShapes().addShape(new_outline);
    }
    ListContainer.getGroupCollection().remove(index);
  for (CreateShapeCommand cs : temp.getShapes()) {
    ListContainer.getShapeList().addShape(cs);
  }
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
