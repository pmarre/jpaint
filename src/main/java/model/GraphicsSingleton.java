package model;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class GraphicsSingleton {

  private static GraphicsSingleton uniqueG2D;
  private static Graphics2D g2d;

  private GraphicsSingleton() {
  }

  public static GraphicsSingleton getInstance() {
    if (uniqueG2D == null) {
      uniqueG2D = new GraphicsSingleton();
    }
    return uniqueG2D;
  }

  public static void setG2D(Graphics2D g) {
    g2d = g;
  }

  public static Graphics2D getG2D() {
    return g2d;
  }

}