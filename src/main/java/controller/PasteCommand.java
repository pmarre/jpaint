package controller;

import java.util.List;
import model.ListContainer;
import model.ShapeList;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;

public class PasteCommand implements ICommand, IUndoable {

  @Override
  public void execute() {

    ShapeList sl = ListContainer.getSelectedShapes();
    System.out.println("in paste command " + sl.getShapes().size());
    for (CreateShapeCommand cs : ListContainer.getCopyList().getShapes()) {
      sl.addShape(cs);
    }
  }

  @Override
  public void undo() {}

  @Override
  public void redo() {}

}
