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
    ICommand cmd;
    ShapeList selected;

    public MouseHandler(ApplicationState appState, PaintCanvasBase paintCanvas, Graphics2D g, ShapeList sl, ShapeList selected) {
        shapeList = new ShapeList();
        this.appState = appState;
        this.paintCanvas = paintCanvas;
        this.g = g;
        this.shapeList = sl;
        this.selected = selected;
    }

    Point start, end;
    public void mousePressed(MouseEvent event) {
        start = event.getPoint();
        System.out.println(start);
    }

    public void mouseReleased(MouseEvent event) {
        end = event.getPoint();
        MouseMode MM = appState.getActiveMouseMode();
        ShapeInfo shapeInfo = new ShapeInfo(appState.getActivePrimaryColor(),
                appState.getActiveSecondaryColor(),
                appState.getActiveShapeType(),
                appState.getActiveShapeShadingType());
        switch (MM) {
            case DRAW:
                cmd = new CreateShapeCommand(appState, paintCanvas, start, end, shapeList);
                CreateShapeCommand csc = new CreateShapeCommand(appState, paintCanvas, start, end, shapeList);
                shapeList.addShape(csc);
                //CommandHistory.add(csc);
                break;

            case MOVE:
                cmd = new MoveCommand(selected, start, end, shapeInfo, shapeList, paintCanvas, appState);
                System.out.println("Mouse in move mode");
                break;

            case SELECT:
                cmd = new SelectCommand(selected, start, end, paintCanvas, appState, shapeList);
                System.out.println("Mouse in select mode");
                break;

            default:
                throw new IllegalStateException("No mouse selected");
        }

       cmd.execute();

    }
}
