package model;

import java.awt.*;

public class ShapeInfo {
    public ShapeColor primaryColor;
    public ShapeColor secondaryColor;
    public ShapeType shape;
    public ShapeShadingType shading;

    public ShapeInfo(ShapeColor primaryColor, ShapeColor secondaryColor, ShapeType shape, ShapeShadingType shading) {
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.shading = shading;
        this.shape = shape;
    }
}
