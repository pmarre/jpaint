package model;

import view.interfaces.PaintCanvasBase;
import controller.*;

public class Undo {
    PaintCanvasBase pc;

    public Undo(PaintCanvasBase paintCanvas) {
        this.pc = paintCanvas;
    }

    public void runUndo() {
       //CommandHistory.undo();
    }
}
