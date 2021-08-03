package controller;

import java.util.ArrayList;
import java.util.Stack;
import model.interfaces.ICommand;
import model.ListContainer;
import model.ShapeList;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;

public class DeleteCommand implements ICommand, IUndoable {
  private static ShapeList selected;
  private static ShapeList shapeList;
  static ArrayList<CreateShapeCommand> deletedShapes = new ArrayList<CreateShapeCommand>();



  @Override
  public void execute() {
    selected = ListContainer.getSelectedShapes();
    shapeList = ListContainer.getShapeList();
    for (CreateShapeCommand cs : selected.getShapes()) {
      ListContainer.getUndoStack().add(cs);
      shapeList.removeShape(cs);
      //cs.pc.repaint();
      deletedShapes.add(cs);
    }
    CommandHistory.add(this);
  }

  @Override
  public void undo() {
    shapeList = ListContainer.getShapeList();
   for (CreateShapeCommand cs : deletedShapes) {
     shapeList.addShape(cs);
   }
  }

  @Override
  public void redo() {
    shapeList = ListContainer.getShapeList();
    for (CreateShapeCommand cs : deletedShapes) {
      shapeList.removeShape(cs);
    }
  }
}
