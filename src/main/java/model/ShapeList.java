package model;

import model.interfaces.IUndoable;
import controller.CreateShapeCommand;
import model.interfaces.IShape;

import java.util.ArrayList;

public class ShapeList {
    private ArrayList<CreateShapeCommand> shapeList;
    private ArrayList<CreateShapeCommand> observers = new ArrayList<CreateShapeCommand>();

    public ShapeList() {
        shapeList = new ArrayList<CreateShapeCommand>();
    }

    public void addShape(CreateShapeCommand shape) {
        shapeList.add(shape);
        //observers = shapeList;

        //shape.shapeInfo.pc.repaint();
        notifyObservers();
        System.out.println("Added to shapelist: " + shape);
    }

    public void removeShape(CreateShapeCommand shape) {

        shapeList.remove(shape);
        //observers = shapeList;
        //shape.shapeInfo.pc.repaint();
        notifyObservers();
        System.out.println("Removed: " + shape);
    }

    public void replaceShape(CreateShapeCommand shape, CreateShapeCommand new_shape) {
        shapeList.remove(shape);
        shapeList.add(new_shape);
        notifyObservers();
        System.out.println("Replaced " + shape + " with " + new_shape);
    }

    public void printList() {
        for (int i = 0; i < shapeList.size(); i++) {
            System.out.println("shape: " + shapeList.indexOf(i));
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
        }
    }

    public ArrayList<CreateShapeCommand> getShapes() {
        return shapeList;
    }

}
