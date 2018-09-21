package com.mass.common.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * BASE64编码解码工具包.
 */
public class Base64Utils {

  /**
   * 文件读取缓冲区大小.
   */
  private static final int CACHE_SIZE = 1024;

  /**
   * BASE64字符串解码为二进制数据.
   */
  public static byte[] decode(String base64) throws Exception {
    return new BASE64Decoder().decodeBuffer(base64);
  }

  /**
   * 二进制数据编码为BASE64字符串.
   */
  public static String encode(byte[] bytes) throws Exception {
    return new String(new BASE64Encoder().encode(bytes));
  }

  /**
   * 将文件编码为BASE64字符串 大文件慎用，可能会导致内存溢出.
   */
  public static String encodeFile(String filePath) throws Exception {
    byte[] bytes = fileToByte(filePath);
    return encode(bytes);
  }

  /**
   * BASE64字符串转回文件.
   */
  public static void decodeToFile(String filePath, String base64) throws Exception {
    byte[] bytes = decode(base64);
    byteArrayToFile(bytes, filePath);
  }

  /**
   * 文件转换为二进制数组.
   */
  public static byte[] fileToByte(String filePath) throws Exception {
    byte[] data = new byte[0];
    File file = new File(filePath);
    if (file.exists()) {
      FileInputStream in = new FileInputStream(file);
      ByteArrayOutputStream out = new ByteArrayOutputStream(2048);
      byte[] cache = new byte[CACHE_SIZE];
      int noRead = 0;
      while ((noRead = in.read(cache)) != -1) {
        out.write(cache, 0, noRead);
        out.flush();
      }
      out.close();
      in.close();
      data = out.toByteArray();
    }
    return data;
  }

  /**
   * 二进制数据写文件.
   */
  public static void byteArrayToFile(byte[] bytes, String filePath) throws Exception {
    InputStream in = new ByteArrayInputStream(bytes);
    File destFile = new File(filePath);
    if (!destFile.getParentFile().exists()) {
      destFile.getParentFile().mkdirs();
    }
    destFile.createNewFile();
    OutputStream out = new FileOutputStream(destFile);
    byte[] cache = new byte[CACHE_SIZE];
    int noRead = 0;
    while ((noRead = in.read(cache)) != -1) {
      out.write(cache, 0, noRead);
      out.flush();
    }
    out.close();
    in.close();
  }


}
