package model;

import controller.CreateShapeCommand;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Stack;

public class ListContainer {

  static ShapeCollection shapeCollection;
  static CopyList copyList;
  static ShapeCollection selected;
  static Stack<CreateShapeCommand> undoStack;
  static Stack<CreateShapeCommand> redoStack;
  static ArrayList<ShapeCollection> groups;

  public ListContainer(ShapeCollection shapeCollection, ShapeCollection selected, CopyList copyList,
      Stack<CreateShapeCommand> undoStack, ArrayList<ShapeCollection> groups) {
    this.shapeCollection = shapeCollection;
    this.copyList = copyList;
    this.selected = selected;
    this.undoStack = undoStack;
    this.groups = groups;
  }

  public static ShapeCollection getShapeList() {
    return shapeCollection;
  }

  public static ShapeCollection getSelectedShapes() {
    return selected;
  }

  public static CopyList getCopyList() {
    return copyList;
  }

  public static Stack<CreateShapeCommand> getUndoStack() {
    return undoStack;
  }

  public static ArrayList<ShapeCollection> getGroupCollection() { return groups;}

}


