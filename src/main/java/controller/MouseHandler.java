package controller;

import model.MouseMode;
import model.ShapeInfo;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;
import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;
import model.ShapeList;
import model.interfaces.ICommand;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MouseHandler extends MouseAdapter {
    ApplicationState appState;
    PaintCanvasBase paintCanvas;
    Graphics2D g;
    ShapeList shapeList;
    ArrayList<CreateShapeCommand> sl;
    DrawShapeCommand cmd;
    ShapeList selected;

    public MouseHandler(ApplicationState appState, PaintCanvasBase paintCanvas) {
        shapeList = new ShapeList();
        selected = new ShapeList();
        this.appState = appState;
        this.paintCanvas = paintCanvas;
//        this.g = g;
//        this.shapeList = sl;
//        this.selected = selected;
    }

    Point start, end;
    public void mousePressed(MouseEvent event) {
        start = event.getPoint();
        System.out.println(start);
    }

    public void mouseReleased(MouseEvent event) {
        end = event.getPoint();
        MouseMode MM = appState.getActiveMouseMode();
        // TEMP CODE:
        double[] start1 = {start.getX(), start.getY()};
        double[] end1 = {end.getX(), end.getY()};
        double width, height, x, y;

        // Switch x's if user drags from r->l
        if (end1[0] > start1[0]) {
            width = end1[0] - start1[0];
            x = start1[0];
        } else {
            width = start1[0] - end1[0];
            x = end1[0];
        }

        // Switch y's if user drags from r->l
        if (end1[1] > start1[1]) {
            height = end1[1] - start1[1];
            y = start1[1];
        } else {
            height = start1[1] - end1[1];
            y = end1[1];
        }

        // END TEMP CODE
        ShapeInfo shapeInfo = new ShapeInfo(appState, start, end, x, y, width, height);
        CreateShapeCommand csc = null;
        if (height < 1 && width < 1) { return; }
        switch (MM) {
            case DRAW:

               // cmd = new DrawShapeCommand(appState, paintCanvas, start, end, shapeList);
                //cmd = new CreateShapeCommand(appState, paintCanvas, start, end, shapeList, shapeInfo);
                csc = new CreateShapeCommand(appState, paintCanvas, start, end, shapeList, shapeInfo);
                //paintCanvas.repaint();
                shapeList.registerObserver(csc);
                shapeList.addShape(csc);
                csc.execute();
                //CommandHistory.add(csc);
                break;

            case MOVE:
                MoveCommand m = new MoveCommand(start, end, selected, shapeList, shapeInfo);
                m.execute();
                System.out.println("Mouse in move mode");
                break;

            case SELECT:
                SelectCommand c = new SelectCommand(selected, start, end, paintCanvas, appState, shapeList);
                c.execute();
                System.out.println("Mouse in select mode");
                break;

            default:
                throw new IllegalStateException("No mouse selected");
        }



    }
}