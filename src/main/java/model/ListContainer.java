package model;

import controller.CreateShapeCommand;
import java.util.Stack;
import model.interfaces.IUndoable;

public class ListContainer {

  static ShapeList shapeList;
  static CopyList copyList;
  static ShapeList selected;
  static Stack<CreateShapeCommand> undoStack;
  static Stack<CreateShapeCommand> redoStack;

  public ListContainer(ShapeList shapeList, ShapeList selected, CopyList copyList, Stack<CreateShapeCommand> undoStack, Stack<CreateShapeCommand> redoStack) {
    this.shapeList = shapeList;
    this.copyList = copyList;
    this.selected = selected;
    this.undoStack = undoStack;
    this.redoStack = redoStack;
  }

  public static ShapeList getShapeList() {
    return shapeList;
  }

  public static ShapeList getSelectedShapes() {
    return selected;
  }

  public static CopyList getCopyList() {
    return copyList;
  }
  public static Stack<CreateShapeCommand> getUndoStack() {return undoStack;}
  public static Stack<CreateShapeCommand> getRedoStack() {return redoStack;}

}


