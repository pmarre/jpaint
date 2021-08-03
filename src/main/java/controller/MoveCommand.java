package controller;

import model.ListContainer;
import model.ShapeInfo;
import model.ShapeList;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.interfaces.IShape;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class MoveCommand implements ICommand, IUndoable {
    static Point start;
    static Point end;
    static ShapeInfo shapeInfo;
    static ShapeList sl;
    static ShapeList selectedShapes;
    static PaintCanvasBase pc;

    public MoveCommand (Point start, Point end) {
       this.start = start;
       this.end = end;
    }

    public static void moveShape() {
        // Calculate the distance moved
        sl = ListContainer.getShapeList();
        selectedShapes = ListContainer.getSelectedShapes();
        double x_move = end.getX() - start.getX();
        double y_move = end.getY() - start.getY();
        System.out.println("selected shapes before move: " + ListContainer.getSelectedShapes().getShapes().size());
        CreateShapeCommand tempShape = null;
        CreateShapeCommand oldShape = null;
        ShapeList tmpOld = new ShapeList();
        ShapeList tmpNew = new ShapeList();
        for (CreateShapeCommand s : selectedShapes.getShapes()) {
            shapeInfo = s.shapeInfo;
            pc = shapeInfo.pc;
            System.out.println("Selected shape: " + shapeInfo.shape);
            Point ns = new Point ((int)(shapeInfo.start.getX() + x_move), (int)(shapeInfo.start.getY() + y_move));
            Point ne = new Point ((int)(shapeInfo.end.getX() + x_move), (int)(shapeInfo.end.getY() + y_move));
            ShapeInfo nsi = new ShapeInfo(shapeInfo.state, shapeInfo.pc, ns, ne , ns.getX(), ns.getY(), shapeInfo.width, shapeInfo.height);
            nsi.shape = shapeInfo.shape;
            nsi.shading = shapeInfo.shading;
            nsi.primaryColor = shapeInfo.primaryColor;
            nsi.secondaryColor = shapeInfo.secondaryColor;
            if (s.shapeInfo.isSelected) {
                nsi.isSelected = true;
            }
            CreateShapeCommand shape = new CreateShapeCommand(shapeInfo.pc, ns, ne, shapeInfo.sl, nsi);
            sl.replaceShape(s, shape);
            tmpNew.addShape(shape);
            tmpOld.addShape(s);
        }

        // Need better solution here
        for (CreateShapeCommand cs : tmpOld.getShapes()) {
            int i = tmpOld.getShapes().indexOf(cs);
            selectedShapes.replaceShape(cs, tmpNew.getShapes().get(i));
        }
        tmpNew.getShapes().clear();
        tmpOld.getShapes().clear();
    }

    @Override
    public void execute() {
        moveShape();
        System.out.println("selected shapes after move: " + ListContainer.getSelectedShapes().getShapes().size());
    }

    @Override
    public void redo() {

    }

    @Override
    public void undo() {

    }
}