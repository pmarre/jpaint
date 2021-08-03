package controller;

import java.util.List;
import javax.swing.BorderFactory;
import model.GraphicsContainer;
import model.ListContainer;
import model.ShapeColor;
import model.ShapeInfo;
import model.ShapeList;
import model.ShapeShadingType;
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
    static ShapeList shapeList;
    static ShapeList selected;
    static ShapeList tempList = new ShapeList();

    public SelectCommand (Point start, Point end, PaintCanvasBase pc, ApplicationState state) {
        this.start = start;
        this.end = end;
        this.pc = pc;
        this.state = state;
    }



    @Override
    public void execute(){
        selected = ListContainer.getSelectedShapes();
        selected.getShapes().clear();
        shapeList = ListContainer.getShapeList();
        tempList = new ShapeList();
        System.out.println(shapeList.getShapes().size());
        for (CreateShapeCommand shape : shapeList.getShapes()) {

            if (shape.getEnd().getX() > start.getX() &&
                    shape.getStart().getX() < end.getX() &&
                    shape.getEnd().getY() > start.getY() &&
                    shape.getStart().getY() < end.getY()) {
                System.out.println("Collision");
                CreateShapeCommand cs = outlineSelected(shape);
                selected.addShape(shape);

                selected.addShape(cs);
                // tempList.addShape(  );
                tempList.addShape(cs);
            }

        }

        for (CreateShapeCommand cs : tempList.getShapes()) {
            shapeList.addShape(cs);
        }


// Hacked together way to remove outline
        if (selected.getShapes().size() == 0) {
            int count = 0;
            int size = shapeList.getShapes().size();
            System.out.println("remove");
            while (count < size) {
                CreateShapeCommand s = shapeList.getShapes().get(count);
                if (s.shapeInfo.isSelected) {
                    shapeList.removeShape(s);
                    size--;
                    System.out.println("remove");
                } else {
                    count++;
                }

            }


        }
        tempList.getShapes().clear();
    }

    public CreateShapeCommand outlineSelected(CreateShapeCommand shape) {
        Graphics2D g2d = GraphicsContainer.getG2D();
        ShapeInfo si = new ShapeInfo(shape.shapeInfo.state, shape.shapeInfo.pc, shape.shapeInfo.start,
            shape.shapeInfo.end, shape.shapeInfo.x - 5, shape.shapeInfo.y - 5, shape.shapeInfo.width + 10,
            shape.shapeInfo.height +10);
        Point ns = new Point((int)si.start.getX() - 5, (int)si.start.getY() - 5);
        Point ne = new Point((int)si.end.getX() - 5, (int)si.end.getY() - 5);
        si.start = ns;
        si.end = ne;
        si.shape = shape.shapeInfo.shape;
        si.secondaryColor = ShapeColor.RED;
        si.shading = ShapeShadingType.OUTLINE;
        si.isSelected = true;
        CreateShapeCommand cs = new CreateShapeCommand(si.pc, si.start, si.end, si.sl, si);
        selected.addShape(cs);
        shape.shapeInfo.pc.repaint();
        System.out.println("border");
        return cs;

    }




}
