package controller;

import java.util.Stack;
import model.ListContainer;
import model.ShapeCollection;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;

public class UngroupCommand implements ICommand, IUndoable {

  public Stack<ShapeCollection> undoStack = new Stack<ShapeCollection>();

  @Override
  public void execute() {
    ShapeCollection temp = new ShapeCollection();
    int index = 0;
    if (ListContainer.getSelectedShapes().getShapes().size() == 0) {
      return;
    }
    for (CreateShapeCommand shape : ListContainer.getSelectedShapes().getShapes()) {
      // if shape is in a group, remove
      if (shape.shapeInfo.inGroup) {
        shape.shapeInfo.inGroup = false;
        for (ShapeCollection group : ListContainer.getGroupCollection()) {
          if (group.getShapes().contains(shape)) {
            index = ListContainer.getGroupCollection().indexOf(group);
          }
        }
      }
      // if shape is a selection border, remove
      if (shape.shapeInfo.isSelected) {
        ListContainer.getShapeList().removeShape(shape);
      }
    }
    undoStack.add(ListContainer.getGroupCollection().get(index));
    ListContainer.getGroupCollection().remove(index);
    for (CreateShapeCommand cs : temp.getShapes()) {
      ListContainer.getShapeList().addShape(cs);
    }
    CommandHistory.add(this);
  }

  @Override
  public void undo() {
    CommandHistory.undo();
  }

  @Override
  public void redo() {
    CommandHistory.redo();
  }
}
