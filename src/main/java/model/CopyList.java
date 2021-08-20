package model;

import controller.CreateShapeCommand;
import java.util.ArrayList;


public class CopyList {

  private ArrayList<CreateShapeCommand> copyList;

  public CopyList() {
    copyList = new ArrayList<CreateShapeCommand>();
  }

  public void addShape(CreateShapeCommand shape) {
    copyList.add(shape);
  }

  public void removeShape(CreateShapeCommand shape) {
    copyList.remove(shape);
  }

  public ArrayList<CreateShapeCommand> getShapes() {
    return copyList;
  }

}



