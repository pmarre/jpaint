package controller;

import java.util.Stack;
import model.interfaces.ICommand;
import model.ListContainer;
import model.ShapeList;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;

public class DeleteCommand implements ICommand, IUndoable {
  private static ShapeList selected;
  private static ShapeList shapeList;
  static Stack<CreateShapeCommand> undo;
  static Stack<CreateShapeCommand> redo;


  @Override
  public void execute() {
    selected = ListContainer.getSelectedShapes();
    shapeList = ListContainer.getShapeList();
    for (CreateShapeCommand cs : selected.getShapes()) {
      ListContainer.getUndoStack().add(cs);
      shapeList.removeShape(cs);
      cs.pc.repaint();
    }
  }

  @Override
  public void undo() {
    undo = ListContainer.getUndoStack();
    CreateShapeCommand c = undo.pop();
    ListContainer.getShapeList().addShape(c);
    ListContainer.getRedoStack().add(c);
  }

  @Override
  public void redo() {
//    redo = ListContainer.getRedoStack();
//    CreateShapeCommand c = redo.pop();
//    ListContainer.getShapeList().removeShape(c);
//    ListContainer.getUndoStack().add(c);
  }
}
