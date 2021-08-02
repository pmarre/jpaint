package controller;

import model.ListContainer;
import model.ShapeInfo;
import model.ShapeList;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.persistence.ApplicationState;
import view.gui.PaintCanvas;

import java.awt.*;
import java.util.ArrayList;

import view.interfaces.PaintCanvasBase;

public class SelectCommand implements ICommand {
    Point start;
    Point end;
    PaintCanvasBase pc;
    ApplicationState state;
    ShapeList shapeList;
    double top_x = -1;
    double bottom_x = -1;
    double top_y = -1;
    double bottom_y = -1;
    ShapeList selected;

    public SelectCommand (ShapeList selected, Point start, Point end, PaintCanvasBase pc, ApplicationState state, ShapeList shapeList) {
        this.start = start;
        this.end = end;
        this.pc = pc;
        this.state = state;
        this.shapeList = shapeList;
        this.selected = selected;
    }



    @Override
    public void execute(){
        for (CreateShapeCommand shape : shapeList.getShapes()) {
            System.out.println("x: " + shape.getStart().getX() + " boundary: " + start.getX());
            if (shape.getEnd().getX() > start.getX() &&
                    shape.getStart().getX() < end.getX() &&
                    shape.getEnd().getY() > start.getY() &&
                    shape.getStart().getY() < end.getY()) {
                System.out.println("Collision");
                selected.addShape(shape);
                ListContainer.getSelectedShapes().addShape(shape);

                if (shape.getStart().getX() >= top_x) {
                    top_x = shape.getStart().getX();
                }

                if (shape.getEnd().getX() >= bottom_x) {
                    bottom_x = shape.getEnd().getX();
                }

                if (shape.getStart().getY() >= top_y) {
                    top_y = shape.getStart().getY();
                }

                if (shape.getEnd().getY() >= bottom_y) {
                    bottom_y = shape.getEnd().getY();
                }


                System.out.println(bottom_x + " " + bottom_y + " " + top_x + " " + top_y);

            } else {
                //System.out.println(selected.getShapes().size());
                for (CreateShapeCommand s : selected.getShapes()) {
                    selected.removeShape(s);
                    ListContainer.getSelectedShapes().removeShape(shape);
                }
                System.out.println("Selected: " + selected.getShapes().size());
            }
        }

//        int x = (int) top_x;
//        int y = (int) top_y;
//        int w = (int) (top_x - bottom_x);
//        int h = (int) (top_y - bottom_y);

       //pc.getGraphics2D().drawRect(x,y,w,h);
        //System.out.println("rect");
        int count = 0;
        for (CreateShapeCommand s : selected.getShapes() ) {
            count++;
        }
        System.out.println("shapes selected: " + count);
    }

}
