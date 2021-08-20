package controller;

import java.awt.Point;
import model.CopyList;
import model.ListContainer;
import model.ShapeInfo;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;

public class CopyCommand implements ICommand, IUndoable {

  static CopyList copyList;

  @Override
  public void execute() {
    copyList = ListContainer.getCopyList();

    // Set offset for paste
    double x_move = 100;
    double y_move = 100;

    for (CreateShapeCommand shapes : ListContainer.getSelectedShapes().getShapes()) {
      if (!shapes.shapeInfo.isSelected) {
        ShapeInfo si = shapes.shapeInfo;
        Point ns = new Point((int) (si.start.getX() + x_move), (int) (si.start.getY() + y_move));
        Point ne = new Point((int) (si.end.getX() + x_move), (int) (si.end.getY() + y_move));
        ShapeInfo nsi = new ShapeInfo(si.state, si.pc, ns, ne, ns.getX(), ns.getY(), si.width,
            si.height);
        nsi.shape = si.shape;
        nsi.shading = si.shading;
        nsi.primaryColor = si.primaryColor;
        nsi.secondaryColor = si.secondaryColor;
        CreateShapeCommand shape = new CreateShapeCommand(si.pc, ns, ne, nsi);
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







