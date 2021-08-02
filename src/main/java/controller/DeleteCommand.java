package controller;

import model.interfaces.ICommand;
import model.ListContainer;
import model.ShapeList;
import model.interfaces.ICommand;

public class DeleteCommand implements ICommand {
  private static ShapeList selected;
  private static ShapeList shapeList;

  @Override
  public void execute() {
    selected = ListContainer.getSelectedShapes();
    shapeList = ListContainer.getShapeList();

    for (CreateShapeCommand cs : selected.getShapes()) {
      shapeList.removeShape(cs);
      cs.pc.repaint();
    }
  }
}
