package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import model.ListContainer;
import model.ShapeList;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;

public class PasteCommand implements ICommand, IUndoable {
  static ArrayList<CreateShapeCommand> pastedList = new ArrayList<CreateShapeCommand>();
  static ShapeList sl = ListContainer.getShapeList();

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
    if (pastedList.size() < 1) return;
    for (CreateShapeCommand shape : pastedList) {
      sl.removeShape(shape);
    }
  }

  @Override
  public void redo() {
    if (pastedList.size() < 1) return;
    for (CreateShapeCommand shape : pastedList) {
      sl.addShape(shape);
    }
  }
}
