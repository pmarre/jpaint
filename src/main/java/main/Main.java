package main;

//import controller.DrawRectangle;
import controller.IJPaintController;
import controller.JPaintController;
import controller.MouseHandler;
import model.MouseMode;
import model.ShapeList;
import model.interfaces.IShape;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
// test

// import controller.CommandHistory;
public class Main {
    public static void main(String[] args){
        PaintCanvasBase paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);
        IJPaintController controller = new JPaintController(uiModule, appState);
        controller.setup();
        ShapeList shapelist = new ShapeList();
        // For example purposes only; remove all lines below from your final project.

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // PaintMouseAdaptor mouse = new PaintMouseAdaptor();

        // Filled in rectangle
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        // IShape iShape = new DrawRectangle(appState, paintCanvas);

        MouseHandler mh = new MouseHandler(appState, paintCanvas, graphics2d, shapelist);
        paintCanvas.addMouseListener(mh);

//        paintCanvas.addMouseListener(new MouseAdapter () {
//           int[] start;
//           int[] end;
//            public void mousePressed(MouseEvent event) {
//
//                    int x = event.getX();
//                    int y = event.getY();
//                    start = iShape.getStartXY(x,y);
//            }
//
//            public void mouseReleased(MouseEvent event) {
//                    int x = event.getX();
//                    int y = event.getY();
//                    end = iShape.getEndXY(x, y);
//                    System.out.println(appState.getActiveMouseMode() + ", " + appState.getActiveShapeType());
//                    if (appState.getActiveMouseMode().equals(MouseMode.DRAW)) iShape.draw(graphics2d, start, end);
//
//                  //  undoStack.add();
//
//            }
//
//        });

//        graphics2d.setColor(Color.GREEN);
//        graphics2d.fillRect(12, 13, 200, 400);
//
//        // Outlined rectangle
//        graphics2d.setStroke(new BasicStroke(5));
//        graphics2d.setColor(Color.BLUE);
//        graphics2d.drawRect(12, 13, 200, 400);
//
//        // Selected Shape
//        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
//        graphics2d.setStroke(stroke);
//        graphics2d.setColor(Color.BLACK);
//        graphics2d.drawRect(7, 8, 210, 410);

        // end of example
    }
}
