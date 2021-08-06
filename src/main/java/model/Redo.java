package model;

import view.interfaces.PaintCanvasBase;
import controller.*;

public class Redo {

  PaintCanvasBase pc;

  public Redo(PaintCanvasBase paintCanvas) {
    this.pc = paintCanvas;
  }

  public void runRedo() {
    //CommandHistory.redo();

  }
}
