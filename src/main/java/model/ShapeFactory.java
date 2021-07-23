//package model;
//
//import controller.DrawEllipse;
//import controller.DrawRectangleStrategy;
//import controller.DrawTriangleStrategy;
//import model.interfaces.ICommand;
//import model.interfaces.IShape;
//import model.interfaces.IShapeFactory;
//import controller.CreateEllipseCommand;
//import controller.CreateRectangleCommand;
//import controller.CreateTriangleCommand;
//import view.interfaces.PaintCanvasBase;
//
//import java.awt.*;
//
//public class ShapeFactory implements IShapeFactory {
//    IShape s;
//    PaintCanvasBase pc;
//    ShapeType shape;
//    ShapeInfo si;
//    public ShapeFactory(ShapeInfo si) {
//        this.pc = si.pc;
//        this.shape = si.shape;
//        this.si = si;
//    }
//    public IShape createShape() {
//        switch(shape){
//            case ELLIPSE:
//                s = new CreateEllipseCommand(si);
//                break;
//            case RECTANGLE:
//                s = new CreateRectangleCommand(si);
//                break;
//            case TRIANGLE:
//                s = new CreateTriangleCommand(si);
//                break;
//            default:
//                throw new IllegalArgumentException("No shape");
//        }
//        System.out.println("THIS IS PRINTING");
//        s.draw(pc.getGraphics2D());
//        return s;
//    }
//
////    @Override
////    public void execute() {
////
////    }
//
//
//}
