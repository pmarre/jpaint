package controller;

import java.util.List;
import model.ListContainer;
import model.ShapeList;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;

public class PasteCommand implements ICommand, IUndoable {

  @Override
  public void execute() {

    ShapeList sl = ListContainer.getShapeList();
    System.out.println("in paste command " + ListContainer.getCopyList().getShapes().size());
    for (CreateShapeCommand cs : ListContainer.getCopyList().getShapes()) {
      sl.addShape(cs);
      System.out.println("x: " + cs.shapeInfo.start.getX());
      System.out.println("Shape added by paste: " + cs);
      System.out.println("SL: " + ListContainer.getShapeList().getShapes().size());
    }

    ListContainer.getCopyList().getShapes().clear();
  }

  @Override
  public void undo() {}

  @Override
  public void redo() {}

}
