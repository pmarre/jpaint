package model.interfaces;

import view.interfaces.PaintCanvasBase;

import java.awt.*;

public interface IShape {
    void draw(PaintCanvasBase g, double[] start, double[] end);
//    int[] getStartXY(int x, int y);
//    int[] getEndXY(int x, int y);
}
