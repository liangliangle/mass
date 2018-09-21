package com.mass.common.utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class LoggerUtil {
  private static final Logger logger = Logger.getLogger(LoggerUtil.class);
  private static final Properties log4jProp = new Properties();

  static {
    try {
      log4jProp
          .load(ConfigurationUtil.class.getClassLoader().getResourceAsStream("log4j.properties"));
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    }
  }

  public static void info(String message, String module) {
    logger.info(Thread.currentThread().getName() + " - " + module + " - " + message);
  }

  public static void error(String message, String module) {
    logger.error(module + " - " + message);
  }
}
