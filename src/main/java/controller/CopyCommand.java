package controller;

import java.awt.Point;
import java.util.List;
import model.CopyList;
import model.ShapeInfo;
import model.ShapeList;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;
import java.util.ArrayList;
import model.ListContainer;

public class CopyCommand implements  ICommand, IUndoable {
  static Point start;
  static Point end;
  static ShapeInfo shapeInfo;
  static ShapeList sl;
  //static ShapeList selectedShapes;
  static CopyList copyList;

  @Override
  public void execute() {
    copyList = ListContainer.getCopyList();
    System.out.println("in copy execute");
    double x_move = 100;
    double y_move = -50;
    System.out.println("selected: " + ListContainer.getSelectedShapes().getShapes().size());
    for (CreateShapeCommand s : ListContainer.getSelectedShapes().getShapes()) {
      ShapeInfo si = s.shapeInfo;
      Point ns = new Point ((int)(si.start.getX() + x_move), (int)(si.start.getY() + y_move));
      Point ne = new Point ((int)(si.end.getX() + x_move), (int)(si.end.getY() + y_move));
      ShapeInfo nsi = new ShapeInfo(si.state, ns, ne , ns.getX(), ns.getY(), si.width, si.height);
      CreateShapeCommand shape = new CreateShapeCommand(si.state, si.pc, ns, ne, si.sl, nsi);
      copyList.addShape(shape);
      System.out.println("x: " + s.shapeInfo.start.getX());
      System.out.println("copy list: " + copyList.getShapes().size());
    }
  }

  @Override
  public void redo() {

  }

  @Override
  public void undo() {

  }
}







