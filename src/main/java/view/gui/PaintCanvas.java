package view.gui;

import controller.CreateShapeCommand;
import model.GraphicsContainer;
import model.ListContainer;
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
        ShapeList sl = ListContainer.getShapeList();
        GraphicsContainer.setG2D(g2d);
        if (sl == null) return;
        int count = 0;
        for (CreateShapeCommand csc : sl.getShapes()) {
            if (count < 1 ) {
                csc.update();
            } else {
                break;
            }
        }

        System.out.println("Time to repaint");
    }
}
