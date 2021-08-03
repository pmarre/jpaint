package main;

//import controller.DrawRectangleStrategy;
import controller.CreateShapeCommand;
import controller.IJPaintController;
import controller.JPaintController;
import controller.MouseHandler;
import java.util.Stack;
import model.CopyList;
import model.GraphicsContainer;
import model.ListContainer;
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
        CopyList copyList = new CopyList();
        Stack<CreateShapeCommand> undoStack = new Stack<CreateShapeCommand>();
        Stack<CreateShapeCommand> redoStack = new Stack<CreateShapeCommand>();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ListContainer listContainer = new ListContainer(shapelist, selectedShapes, copyList, undoStack, redoStack);
        Graphics2D graphics2d = (Graphics2D) paintCanvas.getGraphics();
        GraphicsContainer gc = new GraphicsContainer(graphics2d);
        MouseHandler mh = new MouseHandler(appState, paintCanvas);
        paintCanvas.addMouseListener(mh);
    }
}
