package controller;

import model.interfaces.*;
import java.util.*;

public class ShapeList {
    private ArrayList<CreateShapeCommand> shapeList;

    public ShapeList() {
        shapeList = new ArrayList<CreateShapeCommand>();
    }

    public void addShape(CreateShapeCommand s){
        shapeList.add(s);
        System.out.print("Shape added");
    }

    public void removeShape(CreateShapeCommand s){
        shapeList.remove(s);
        System.out.print("Shape removed");
    }
}
