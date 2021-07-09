package model;

import model.interfaces.IUndoable;
import controller.CreateShapeCommand;


import java.util.ArrayList;

public class ShapeList {
    private ArrayList<CreateShapeCommand> shapeList;

    public ShapeList() {
        shapeList = new ArrayList<>();
    }

    public void addShape(CreateShapeCommand shape) {
        shapeList.add(shape);
        System.out.println("Added: " + shape);
    }

    public void removeShape(CreateShapeCommand shape) {
        shapeList.remove(shape);
        for (CreateShapeCommand c : shapeList) {
            c.execute();
        }
        System.out.println("Removed: " + shape);
    }

    public void printList() {
        for (int i = 0; i < shapeList.size(); i++) {
            System.out.println("shape: " + shapeList.indexOf(i));
        }

    }

    public ArrayList<CreateShapeCommand> getShapes() {
        return shapeList;
    }

}
