package model;

import java.awt.Paint;
import model.interfaces.IUndoable;
import controller.CreateShapeCommand;
import model.interfaces.IShape;

import java.util.ArrayList;
import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;

public class ShapeList {
    private ArrayList<CreateShapeCommand> shapeList;
    private ArrayList<CreateShapeCommand> observers = new ArrayList<CreateShapeCommand>();
    static PaintCanvasBase pc = new PaintCanvas();
    public ShapeList() {
        shapeList = new ArrayList<CreateShapeCommand>();
    }

    public void addShape(CreateShapeCommand shape) {
        shapeList.add(shape);
        notifyObservers();
        System.out.println("Added to shapelist: " + shape);
    }

    public void removeShape(CreateShapeCommand shape) {
        shapeList.remove(shape);
        notifyObservers();
        System.out.println("Removed: " + shape);
    }

    public void replaceShape(CreateShapeCommand shape, CreateShapeCommand new_shape) {
        shapeList.remove(shape);
        shapeList.add(new_shape);
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
        for(CreateShapeCommand observer: observers) {
            observer.update();
            observer.shapeInfo.pc.repaint();
        }
    }

    public ArrayList<CreateShapeCommand> getShapes() {
        return shapeList;
    }

}
