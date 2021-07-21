//package controller;
//import model.interfaces.IShape;
//
//import java.awt.*;
//import view.gui.PaintCanvas;
//import view.interfaces.PaintCanvasBase;
//
//public class DrawShape implements IShape {
//    static Graphics2D g2d;
//
//    public DrawShape(PaintCanvasBase pc) {
//        g2d = pc.getGraphics2D();
//    }
//
//    public static void DrawStrategy(CreateShapeCommand csc, DrawShape ds) {
//        IShape shape;
//
//        switch (csc.getShape()) {
//            case ELLIPSE:
//                shape = new DrawEllipse();
//                break;
//            case RECTANGLE:
//                shape = new DrawRectangle();
//                break;
//            case TRIANGLE:
//                shape = new DrawTriangle();
//                break;
//            default:
//                throw new IllegalArgumentException("Add shape");
//        }
//        shape.draw(g2d);
//
//
//    }
//    public void draw(Graphics2D g) {}
//}
