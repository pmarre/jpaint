package controller;

import static controller.DrawShapeCommand.DrawStrategy;

import model.ListContainer;
import model.ShapeInfo;
import model.ShapeType;
import model.ShapeList;
import model.Undo;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.lang.*;
import java.util.*;
import controller.DrawShapeCommand.*;

public class CreateShapeCommand implements ICommand, IUndoable {

  //public Undo shapeInfo;
  ApplicationState appState;
    PaintCanvasBase pc;
    Point start;
    Point end;
    ShapeList shapelist;
    ArrayList<CreateShapeCommand> sl;
    Stack<CreateShapeCommand> undoStack = new Stack<CreateShapeCommand>();
    Stack<CreateShapeCommand> redoStack = new Stack<CreateShapeCommand>();
    CreateShapeCommand csc;
   public  ShapeInfo shapeInfo;


    public CreateShapeCommand(PaintCanvasBase pc, Point start, Point end, ShapeList sl, ShapeInfo si) {

        this.appState = si.state;
        this.pc = pc;
        this.start = start;
        this.end = end;
        this.shapeInfo = si;
    }

// THIS MAY NEED TO BE REMOVED:

    public void update() {
      shapelist = ListContainer.getShapeList();
      for (CreateShapeCommand s : shapelist.getShapes()) {
        DrawStrategy(s, s.shapeInfo);
      }
    }

    public Point getStart() {
        return start.getLocation();
    }

    public Point getEnd() {
        return end.getLocation();
    }



    public void execute() {
        CommandHistory.add(this);
        ListContainer.getUndoStack().add(this);
    }


    public Point[] getXY() {
        Point[] xy = {start, end};
        return xy;
    }

    @Override
    public void undo() {
        System.out.println("undo");
        sl = ListContainer.getShapeList().getShapes();
        undoStack = ListContainer.getUndoStack();
        System.out.println(sl.size());
        if (undoStack.size() == 0) return;
        CreateShapeCommand c = undoStack.pop();
        ListContainer.getShapeList().removeShape(c);
        redoStack.add(c);
        System.out.println(sl.size());
    }

    @Override
    public void redo() {
        System.out.println("redo");
        if (redoStack.size() == 0) return;
        CreateShapeCommand c = redoStack.pop();
        ListContainer.getShapeList().addShape(c);
        undoStack.add(c);
    }
}
