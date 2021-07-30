package main;

//import controller.DrawRectangleStrategy;
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
        ShapeList selectedShapes = new ShapeList();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        MouseHandler mh = new MouseHandler(appState, paintCanvas);
        paintCanvas.addMouseListener(mh);
        paintCanvas.repaint();
    }
}
