package controller;

import java.util.ArrayList;
import model.interfaces.ICommand;
import model.ListContainer;
import model.ShapeCollection;
import model.interfaces.IUndoable;

public class DeleteCommand implements ICommand, IUndoable {

  private static ShapeCollection selected;
  private static ShapeCollection shapeCollection;
  static ArrayList<CreateShapeCommand> deletedShapes = new ArrayList<CreateShapeCommand>();

  @Override
  public void execute() {
    selected = ListContainer.getSelectedShapes();
    shapeCollection = ListContainer.getShapeList();
    for (CreateShapeCommand cs : selected.getShapes()) {
      ListContainer.getUndoStack().add(cs);
      shapeCollection.removeShape(cs);
      deletedShapes.add(cs);
    }
    CommandHistory.add(this);
  }

  @Override
  public void undo() {
    shapeCollection = ListContainer.getShapeList();
    for (CreateShapeCommand cs : deletedShapes) {
      shapeCollection.addShape(cs);
    }
  }

  @Override
  public void redo() {
    shapeCollection = ListContainer.getShapeList();
    for (CreateShapeCommand cs : deletedShapes) {
      shapeCollection.removeShape(cs);
    }
  }
}
