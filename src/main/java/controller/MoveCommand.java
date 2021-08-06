package controller;

import model.ListContainer;
import model.ShapeCollection;
import model.ShapeInfo;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class MoveCommand implements ICommand, IUndoable {

  static Point start;
  static Point end;
  static ShapeInfo shapeInfo;
  static ShapeCollection sl;
  static ShapeCollection selectedShapes;
  static PaintCanvasBase pc;
  static ArrayList<CreateShapeCommand> oldL = new ArrayList<CreateShapeCommand>();
  static ArrayList<CreateShapeCommand> newL = new ArrayList<CreateShapeCommand>();

  public MoveCommand(Point start, Point end) {
    this.start = start;
    this.end = end;
  }

  public static void moveShape() {
    // Calculate the distance moved
    sl = ListContainer.getShapeList();
    selectedShapes = ListContainer.getSelectedShapes();
    double x_move = end.getX() - start.getX();
    double y_move = end.getY() - start.getY();
    System.out.println(
        "selected shapes before move: " + ListContainer.getSelectedShapes().getShapes().size());
    CreateShapeCommand tempShape = null;
    CreateShapeCommand oldShape = null;
    ShapeCollection tmpOld = new ShapeCollection();
    ShapeCollection tmpNew = new ShapeCollection();
    int count = 1;
    for (CreateShapeCommand s : selectedShapes.getShapes()) {
      shapeInfo = s.shapeInfo;
      pc = shapeInfo.pc;
      System.out.println("Selected shape: " + shapeInfo.shape);
      Point ns = new Point((int) (shapeInfo.start.getX() + x_move),
          (int) (shapeInfo.start.getY() + y_move));
      Point ne = new Point((int) (shapeInfo.end.getX() + x_move),
          (int) (shapeInfo.end.getY() + y_move));
      ShapeInfo nsi = new ShapeInfo(shapeInfo.state, shapeInfo.pc, ns, ne, ns.getX(), ns.getY(),
          shapeInfo.width, shapeInfo.height);
      nsi.shape = shapeInfo.shape;
      nsi.shading = shapeInfo.shading;
      nsi.primaryColor = shapeInfo.primaryColor;
      nsi.secondaryColor = shapeInfo.secondaryColor;
      if (s.shapeInfo.isSelected) {
        nsi.isSelected = true;
      }
      CreateShapeCommand shape = new CreateShapeCommand(shapeInfo.pc, ns, ne, shapeInfo.sl, nsi);
      sl.replaceShape(s, shape);
      tmpNew.addShape(shape);
      tmpOld.addShape(s);
      oldL.add(s);
      newL.add(shape);
      System.out.println("Swap #: " + count);
      count++;
    }

    // Need better solution here

    for (CreateShapeCommand cs : tmpOld.getShapes()) {
      int i = tmpOld.getShapes().indexOf(cs);
      selectedShapes.replaceShape(cs, tmpNew.getShapes().get(i));

    }
    tmpNew.getShapes().clear();
    tmpOld.getShapes().clear();

  }

  @Override
  public void execute() {
    moveShape();
    CommandHistory.add(this);
    System.out.println(
        "selected shapes after move: " + ListContainer.getSelectedShapes().getShapes().size());
  }

  @Override
  public void undo() {
    CommandHistory.undo();
//       Temporary fix, only works with one move. If you move more than once then it prints all shapes
    for (CreateShapeCommand cs : newL) {
      sl.removeShape(cs);
    }
    for (CreateShapeCommand cs : oldL) {
      sl.addShape(cs);
    }
  }

  @Override
  public void redo() {
    //       Temporary fix, only works with one move. If you move more than once then it prints all shapes
    for (CreateShapeCommand cs : newL) {
      sl.addShape(cs);
    }
    for (CreateShapeCommand cs : oldL) {
      sl.removeShape(cs);
    }
  }
}