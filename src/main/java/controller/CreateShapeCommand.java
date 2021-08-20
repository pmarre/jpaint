package controller;

import static controller.DrawShapeCommand.DrawStrategy;

import java.awt.Point;
import model.ListContainer;
import model.ShapeCollection;
import model.ShapeInfo;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

public class CreateShapeCommand implements ICommand, IUndoable {

  public ShapeInfo shapeInfo;
  ApplicationState appState;
  PaintCanvasBase pc;
  Point start;
  Point end;
  ShapeCollection shapelist;

  public CreateShapeCommand(PaintCanvasBase pc, Point start, Point end, ShapeInfo si) {
    this.appState = si.state;
    this.pc = pc;
    this.start = start;
    this.end = end;
    this.shapeInfo = si;
  }


  public void update() {
    shapelist = ListContainer.getShapeList();
    for (CreateShapeCommand s : shapelist.getShapes()) {
      DrawStrategy(s);
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
  }


  public Point[] getXY() {
    Point[] xy = {start, end};
    return xy;
  }

  @Override
  public void undo() {
    CreateShapeCommand outline;
    if (this.shapeInfo.outlineShape != null) {
      outline = this.shapeInfo.outlineShape;
      ListContainer.getShapeList().removeShape(outline);
    }
    ListContainer.getShapeList().removeShape(this);
  }

  @Override
  public void redo() {
    ListContainer.getShapeList().addShape(this);
  }
}
