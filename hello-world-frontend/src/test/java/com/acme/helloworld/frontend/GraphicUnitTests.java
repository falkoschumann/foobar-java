/*
 * Foobar - Frontend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.frontend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GraphicUnitTests {
  // 48px = 36pt = 12.7mm = 0.5in

  @Test
  void testConvertPixelToPixel() {
    var result = GraphicUnit.PIXEL.toPixel(48.0);

    assertEquals(48.0, result, 0.00001);
  }

  @Test
  void testConvertPixelToPoint() {
    var result = GraphicUnit.PIXEL.toPoint(48.0);

    assertEquals(36.0, result, 0.00001);
  }

  @Test
  void testConvertPixelToMillimeter() {
    var result = GraphicUnit.PIXEL.toMillimeter(48.0);

    assertEquals(12.7, result, 0.00001);
  }

  @Test
  void testConvertPixelToInch() {
    var result = GraphicUnit.PIXEL.toInch(48.0);

    assertEquals(0.5, result, 0.00001);
  }

  @Test
  void testConvertPointToPixel() {
    var result = GraphicUnit.POINT.toPixel(36.0);

    assertEquals(48.0, result, 0.00001);
  }

  @Test
  void testConvertPointToPoint() {
    var result = GraphicUnit.POINT.toPoint(36.0);

    assertEquals(36.0, result, 0.00001);
  }

  @Test
  void testConvertPointToMillimeter() {
    var result = GraphicUnit.POINT.toMillimeter(36.0);

    assertEquals(12.7, result, 0.00001);
  }

  @Test
  void testConvertPointToInch() {
    var result = GraphicUnit.POINT.toInch(36.0);

    assertEquals(0.5, result, 0.00001);
  }

  @Test
  void testConvertMillimeterToPixel() {
    var result = GraphicUnit.MILLIMETER.toPixel(12.7);

    assertEquals(48, result, 0.00001);
  }

  @Test
  void testConvertMillimeterToPoint() {
    var result = GraphicUnit.MILLIMETER.toPoint(12.7);

    assertEquals(36, result, 0.00001);
  }

  @Test
  void testConvertMillimeterToMillimeter() {
    var result = GraphicUnit.MILLIMETER.toMillimeter(12.7);

    assertEquals(12.7, result, 0.00001);
  }

  @Test
  void testConvertMillimeterToInch() {
    var result = GraphicUnit.MILLIMETER.toInch(12.7);

    assertEquals(0.5, result, 0.00001);
  }

  @Test
  void testConvertInchToPixel() {
    var result = GraphicUnit.INCH.toPixel(0.5);

    assertEquals(48, result, 0.00001);
  }

  @Test
  void testConvertInchToPoint() {
    var result = GraphicUnit.INCH.toPoint(0.5);

    assertEquals(36, result, 0.00001);
  }

  @Test
  void testConvertInchToMillimeter() {
    var result = GraphicUnit.INCH.toMillimeter(0.5);

    assertEquals(12.7, result, 0.00001);
  }

  @Test
  void testConvertInchToInch() {
    var result = GraphicUnit.INCH.toInch(0.5);

    assertEquals(0.5, result, 0.00001);
  }
}
