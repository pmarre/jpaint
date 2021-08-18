package view.gui;

import controller.CreateShapeCommand;
import model.GraphicsSingleton;
import model.ListContainer;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import model.ShapeCollection;


public class PaintCanvas extends PaintCanvasBase {

  public Graphics2D getGraphics2D() {
    return (Graphics2D) getGraphics();
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
    ShapeCollection sl = ListContainer.getShapeList();
    GraphicsSingleton.getInstance().setG2D(g2d);
      if (sl == null) {
          return;
      }
    sl.getShapes().get(0).update();

      if (ListContainer.getSelectedShapes().getShapes().size() > 0) {
          ListContainer.getSelectedShapes().getShapes().get(0).update();
        }


    System.out.println("Time to repaint");
  }
}
