package model;

import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class ShapeInfo {

  public ShapeColor primaryColor;
  public ShapeColor secondaryColor;
  public ShapeType shape;
  public ShapeShadingType shading;
  public Point start;
  public Point end;
  public ShapeCollection sl;
  public ApplicationState state;
  public PaintCanvasBase pc;
  public double width;
  public double height;
  public double x;
  public double y;
  public boolean isSelected;


  public ShapeInfo(ApplicationState state, PaintCanvasBase pc, Point start,
      Point end, double x, double y, double w, double h) {
    this.primaryColor = state.getActivePrimaryColor();
    this.secondaryColor = state.getActiveSecondaryColor();
    this.shading = state.getActiveShapeShadingType();
    this.shape = state.getActiveShapeType();
    this.start = start;
    this.end = end;
    this.state = state;
    this.width = w;
    this.height = h;
    this.x = x;
    this.y = y;
    this.pc = pc;
    this.isSelected = false;
  }
}
