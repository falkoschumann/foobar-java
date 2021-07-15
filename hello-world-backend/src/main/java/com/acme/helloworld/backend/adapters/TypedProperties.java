/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.adapters;

import java.util.Base64;
import java.util.Properties;

public class TypedProperties extends Properties {
  public TypedProperties() {}

  public TypedProperties(int initialCapacity) {
    super(initialCapacity);
  }

  public TypedProperties(Properties defaults) {
    super(defaults);
  }

  public String getString(String key, String defaultValue) {
    return getProperty(key, defaultValue);
  }

  public void setString(String key, String value) {
    setProperty(key, value);
  }

  public int getInt(String key, int defaultValue) {
    var result = defaultValue;
    try {
      String value = getProperty(key);
      if (value != null) result = Integer.parseInt(value);
    } catch (NumberFormatException ignored) {
    }
    return result;
  }

  public void setInt(String key, int value) {
    setProperty(key, Integer.toString(value));
  }

  public long getLong(String key, long defaultValue) {
    var result = defaultValue;
    try {
      String value = getProperty(key);
      if (value != null) result = Long.parseLong(value);
    } catch (NumberFormatException ignored) {
    }
    return result;
  }

  public void setLong(String key, long value) {
    setProperty(key, Long.toString(value));
  }

  public boolean getBoolean(String key, boolean defaultValue) {
    var result = defaultValue;
    try {
      String value = getProperty(key);
      if (value != null) result = Boolean.parseBoolean(value);
    } catch (NumberFormatException ignored) {
    }
    return result;
  }

  public void setBoolean(String key, boolean value) {}

  public float getFloat(String key, float defaultValue) {
    var result = defaultValue;
    try {
      String value = getProperty(key);
      if (value != null) result = Float.parseFloat(value);
    } catch (NumberFormatException ignored) {
    }
    return result;
  }

  public void setFloat(String key, float value) {
    setProperty(key, Float.toString(value));
  }

  public double getDouble(String key, double defaultValue) {
    var result = defaultValue;
    try {
      String value = getProperty(key);
      if (value != null) result = Double.parseDouble(value);
    } catch (NumberFormatException ignored) {
    }
    return result;
  }

  public void setDouble(String key, double value) {
    setProperty(key, Double.toString(value));
  }

  public byte[] getByteArray(String key, byte[] defaultValue) {
    var result = defaultValue;
    try {
      String value = getProperty(key);
      if (value != null) {
        var decode = Base64.getDecoder();
        result = decode.decode(value);
      }
    } catch (IllegalArgumentException ignored) {
    }
    return result;
  }

  public void setByteArray(String key, byte[] value) {
    var encoder = Base64.getEncoder();
    setProperty(key, encoder.encodeToString(value));
  }
}
