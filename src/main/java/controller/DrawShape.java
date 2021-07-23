//package controller;
//import model.interfaces.IShape;
//
//import java.awt.*;
//import view.gui.PaintCanvas;
//import view.interfaces.PaintCanvasBase;
//import model.ShapeInfo;
//
//public class DrawShape implements IShape {
//    static Graphics2D g2d;
//    static IShape shape;
//
//    public DrawShape(PaintCanvasBase pc) {
//        g2d = pc.getGraphics2D();
//    }
//
//    public static void DrawStrategy(IShape shape, ShapeInfo si) {
//        this.shape = shape;
//
//        switch (si.shape) {
//            case ELLIPSE:
//                shape = new DrawEllipse();
//                break;
//            case RECTANGLE:
//                shape = new DrawRectangleStrategy();
//                break;
//            case TRIANGLE:
//                shape = new DrawTriangleStrategy();
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
