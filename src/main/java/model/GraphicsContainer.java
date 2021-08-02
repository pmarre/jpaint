package model;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GraphicsContainer {

  public static Graphics2D g2d;

  public GraphicsContainer(Graphics2D g) {
    this.g2d = g;
  }

  public static void setG2D(Graphics2D g) {
    g2d = g;
  }

  public static Graphics2D getG2D() {
    return g2d;
  }

}
