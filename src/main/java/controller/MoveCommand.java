package controller;

import model.ShapeInfo;
import model.interfaces.ICommand;

import java.awt.*;
import java.util.ArrayList;

public class MoveCommand implements ICommand {
    ArrayList<CreateShapeCommand> selectedShapes = new ArrayList<CreateShapeCommand>();
    Point start;
    Point end;
    ShapeInfo shapeInfo;

    public MoveCommand (ArrayList<CreateShapeCommand> selected, Point start, Point end, ShapeInfo shapeInfo) {
        this.selectedShapes = selected;
        this.start = start;
        this.end = end;
        this.shapeInfo = shapeInfo;
    }

    public void execute() {
        for (CreateShapeCommand shape : selectedShapes) {
            double deltaX = shape.getStart().getX() - start.getX();
            double deltaY = shape.getStart().getY() - start.getY();

            CreateShapeCommand x = shape;
        }
    }

}
