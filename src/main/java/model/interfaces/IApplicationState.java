package model.interfaces;

import model.MouseMode;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;

public interface IApplicationState {

  void setActiveShape();

  void setActivePrimaryColor();

  void setActiveSecondaryColor();

  void setActiveShadingType();

  void setActiveStartAndEndPointMode();

  ShapeType getActiveShapeType();

  ShapeColor getActivePrimaryColor();

  ShapeColor getActiveSecondaryColor();

  ShapeShadingType getActiveShapeShadingType();

  MouseMode getActiveMouseMode();

}
