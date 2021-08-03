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

    double x_move = 100;
    double y_move = 100;

    for (CreateShapeCommand s : ListContainer.getSelectedShapes().getShapes()) {
      if (!s.shapeInfo.isSelected) {
        ShapeInfo si = s.shapeInfo;
        Point ns = new Point((int) (si.start.getX() + x_move), (int) (si.start.getY() + y_move));
        Point ne = new Point((int) (si.end.getX() + x_move), (int) (si.end.getY() + y_move));
        ShapeInfo nsi = new ShapeInfo(si.state, si.pc, ns, ne, ns.getX(), ns.getY(), si.width,
            si.height);
        nsi.shape = si.shape;
        nsi.shading = si.shading;
        nsi.primaryColor = si.primaryColor;
        nsi.secondaryColor = si.secondaryColor;
        CreateShapeCommand shape = new CreateShapeCommand(si.pc, ns, ne, si.sl, nsi);
        copyList.addShape(shape);
        CommandHistory.add(this);
      }
    }
  }

  @Override
  public void redo() {

  }

  @Override
  public void undo() {

  }
}







