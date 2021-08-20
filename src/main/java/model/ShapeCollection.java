package model;

import controller.CreateShapeCommand;
import java.util.ArrayList;
import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;

public class ShapeCollection {

  static PaintCanvasBase pc = new PaintCanvas();
  private ArrayList<CreateShapeCommand> shapeList;
  private ArrayList<CreateShapeCommand> observers = new ArrayList<CreateShapeCommand>();

  public ShapeCollection() {
    shapeList = new ArrayList<CreateShapeCommand>();
  }

  public void addShape(CreateShapeCommand shape) {
    shapeList.add(shape);
    notifyObservers();
  }

  public void removeShape(CreateShapeCommand shape) {
    shapeList.remove(shape);
    notifyObservers();
  }


  public void replaceShape(CreateShapeCommand shape, CreateShapeCommand new_shape) {
    shapeList.remove(shape);
    shapeList.add(new_shape);
    notifyObservers();
  }

  public void clearShapes() {
    shapeList.clear();
    notifyObservers();
  }

  public void updateCanvas() {
    notifyObservers();
  }

  public void printList() {
    for (CreateShapeCommand cs : shapeList) {
      System.out.println("shape: " + cs);
    }

  }

  //@Override
  public void registerObserver(CreateShapeCommand observer) {
    observers.add(observer);
  }


  //@Override
  public void notifyObservers() {
    for (CreateShapeCommand observer : observers) {
      observer.update();
      observer.shapeInfo.pc.repaint();
    }
  }

  public ArrayList<CreateShapeCommand> getShapes() {
    return shapeList;
  }

}
