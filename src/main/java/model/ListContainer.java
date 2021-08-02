package model;

public class ListContainer {

  static ShapeList shapeList;
  static CopyList copyList;
  static ShapeList selected;

  public ListContainer(ShapeList shapeList, ShapeList selected, CopyList copyList) {
    this.shapeList = shapeList;
    this.copyList = copyList;
    this.selected = selected;
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

}
