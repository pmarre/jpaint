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
    public ShapeList sl;
    public ApplicationState state;
    public PaintCanvasBase pc;


    public ShapeInfo(ApplicationState state, PaintCanvasBase pc, Point start,
                     Point end, ShapeList sl ) {
        this.primaryColor = state.getActivePrimaryColor();
        this.secondaryColor = state.getActiveSecondaryColor();
        this.shading = state.getActiveShapeShadingType();
        this.shape = state.getActiveShapeType();
        this.start = start;
        this.end = end;
        this.sl = sl;
        this.state = state;
        this.pc = pc;
    }
}
