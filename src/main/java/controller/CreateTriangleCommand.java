package controller;

import model.ShapeInfo;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class CreateTriangleCommand implements ICommand, IUndoable, IShape {
    public ShapeInfo si;
    public PaintCanvasBase pc;
    public Point start;
    public Point end;

    public CreateTriangleCommand(ShapeInfo si) {
        this.si = si;
        this.pc = si.pc;
        this.start = si.start;
        this.end = si.end;
    }



    @Override
    public void execute() {
        double[] start1 = {start.getX(), start.getY()};
        double[] end1 = {end.getX(), end.getY()};
        double width, height, x, y;

        System.out.println("S_X: " + start1[0] + " S_Y: " + start1[0] + " E_X: " + end1[0] + " E_Y: " + end1[1]);

        // Switch x's if user drags from r->l
        if (end1[0] > start1[0]) {
            width = end1[0] - start1[0];
            x = start1[0];
        } else {
            width = start1[0] - end1[0];
            x = end1[0];
        }

        // Switch y's if user drags from r->l
        if (end1[1] > start1[1]) {
            height = end1[1] - start1[1];
            y = start1[1];
        } else {
            height = start1[1] - end1[1];
            y = end1[1];
        }
        IShape rec = new DrawRectangleStrategy(si.state, pc, x, y, width, height, si.sl);
        rec.draw(pc.getGraphics2D());
        CommandHistory.add(this);

    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

    @Override
    public void draw(Graphics2D g) {

    }

}
