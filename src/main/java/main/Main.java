package main;

//import controller.DrawRectangleStrategy;

import controller.CreateShapeCommand;
import controller.IJPaintController;
import controller.JPaintController;
import controller.MouseHandler;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Stack;
import model.CopyList;
import model.GraphicsSingleton;
import model.ListContainer;
import model.ShapeCollection;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;
import view.interfaces.PaintCanvasBase;
// test

// import controller.CommandHistory;
public class Main {

  public static void main(String[] args) {
    PaintCanvasBase paintCanvas = new PaintCanvas();
    IGuiWindow guiWindow = new GuiWindow(paintCanvas);
    IUiModule uiModule = new Gui(guiWindow);
    ApplicationState appState = new ApplicationState(uiModule);
    IJPaintController controller = new JPaintController(uiModule, appState);
    controller.setup();
    ShapeCollection shapelist = new ShapeCollection();
    ShapeCollection selectedShapes = new ShapeCollection();
    CopyList copyList = new CopyList();
    Stack<CreateShapeCommand> undoStack = new Stack<CreateShapeCommand>();
    ArrayList<ShapeCollection> groups = new ArrayList<ShapeCollection>();

    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    ListContainer listContainer = new ListContainer(shapelist, selectedShapes, copyList, undoStack,
        groups);
    Graphics2D graphics2d = (Graphics2D) paintCanvas.getGraphics();
    GraphicsSingleton gs = GraphicsSingleton.getInstance();
    gs.setG2D(graphics2d);
    MouseHandler mh = new MouseHandler(appState, paintCanvas);
    paintCanvas.addMouseListener(mh);
  }
}
