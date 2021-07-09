/*
 * Foobar - Frontend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.frontend;

public enum GraphicUnit {
  PIXEL(GraphicUnit.PIXEL_SCALE),
  POINT(GraphicUnit.POINT_SCALE),
  MILLIMETER(GraphicUnit.MILLIMETER_SCALE),
  INCH(GraphicUnit.INCH_SCALE);

  private static final double PIXEL_SCALE = 3.0 / 4.0;
  private static final double POINT_SCALE = 1.0 / 72.0;
  private static final double MILLIMETER_SCALE = 1.0;
  private static final double INCH_SCALE = 25.4;

  private final double pixelScale;
  private final double pointScale;
  private final double millimeterScale;
  private final double inchScale;

  GraphicUnit(double scale) {
    if (scale == PIXEL_SCALE) {
      pixelScale = 1.0;
      pointScale = PIXEL_SCALE;
      inchScale = pointScale * POINT_SCALE;
      millimeterScale = inchScale * INCH_SCALE;
    } else if (scale == POINT_SCALE) {
      pointScale = 1.0;
      pixelScale = 1.0 / PIXEL_SCALE;
      inchScale = POINT_SCALE;
      millimeterScale = inchScale * INCH_SCALE;
    } else if (scale == MILLIMETER_SCALE) {
      millimeterScale = 1.0;
      inchScale = 1.0 / INCH_SCALE;
      pointScale = inchScale / POINT_SCALE;
      pixelScale = pointScale / PIXEL_SCALE;
    } else if (scale == INCH_SCALE) {
      inchScale = 1.0;
      millimeterScale = INCH_SCALE;
      pointScale = 1.0 / POINT_SCALE;
      pixelScale = pointScale / PIXEL_SCALE;
    } else {
      throw new IllegalStateException("unreachable code");
    }
  }

  public double toPixel(double value) {
    return value * pixelScale;
  }

  public double toPoint(double value) {
    return value * pointScale;
  }

  public double toMillimeter(double value) {
    return value * millimeterScale;
  }

  public double toInch(double value) {
    return value * inchScale;
  }
}
