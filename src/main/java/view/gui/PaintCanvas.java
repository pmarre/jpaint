package view.gui;

import controller.CreateShapeCommand;
import view.interfaces.PaintCanvasBase;

import javax.swing.JComponent;
import java.awt.*;
import model.ShapeList;


public class PaintCanvas extends PaintCanvasBase {
    public Graphics2D getGraphics2D() {
        return (Graphics2D)getGraphics();
    }

    @Override
    /**
     * This is an event handler.  If this function gets called, its time to
     * draw the entire picture.
     * It you want to force a paint event, call aPaintCanvas.repaint()
     */
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paint(g2d);
        ShapeList sl = new ShapeList();
        System.out.println("print shapes");
        for (CreateShapeCommand c : sl.getShapes()) {
            System.out.println("shape");
            c.update();
        }
        System.out.println("Time to repaint");
    }
}
