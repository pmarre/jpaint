package controller;

import java.util.ArrayList;
import model.ListContainer;
import model.ShapeCollection;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;

public class PasteCommand implements ICommand, IUndoable {

  static ArrayList<CreateShapeCommand> pastedList = new ArrayList<CreateShapeCommand>();
  static ShapeCollection sl = ListContainer.getShapeList();

  @Override
  public void execute() {

    for (CreateShapeCommand cs : ListContainer.getCopyList().getShapes()) {
      sl.addShape(cs);
      pastedList.add(cs);
    }
    ListContainer.getCopyList().getShapes().clear();
    CommandHistory.add(this);
  }

  @Override
  public void undo() {
    if (pastedList.size() < 1) {
      return;
    }
    for (CreateShapeCommand shape : pastedList) {
      sl.removeShape(shape);
    }
  }

  @Override
  public void redo() {
    if (pastedList.size() < 1) {
      return;
    }
    for (CreateShapeCommand shape : pastedList) {
      sl.addShape(shape);
    }
  }
}
