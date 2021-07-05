package model.interfaces;

import java.awt.*;

public interface IShape {
    void draw(Graphics2D g, int[] start, int[] end);

    int[] getStartXY(int x, int y);
    int[] getEndXY(int x, int y);
}
