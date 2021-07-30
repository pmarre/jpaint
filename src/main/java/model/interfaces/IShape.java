package model.interfaces;

import controller.CreateShapeCommand;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public interface IShape {
    public void draw(Graphics2D g, CreateShapeCommand csc);
}
