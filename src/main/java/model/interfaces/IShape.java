package model.interfaces;

import controller.CreateShapeCommand;
import java.awt.Graphics2D;

public interface IShape {

  public void draw(Graphics2D g, CreateShapeCommand csc);
}
