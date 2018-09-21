package com.mass.common.utils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 验证码生成器.
 * 
 */
public class SCaptcha {
  // 图片的宽度。
  private int width = 160;

  // 图片的高度。
  private int height = 40;

  // 验证码字符个数
  private int codeCount = 5;

  // 验证码干扰线数
  private int lineCount = 10;

  // 验证码
  private String code = null;

  // 验证码图片Buffer
  private BufferedImage buffImg = null;

  private char[] codeSequence = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

  public SCaptcha() {
    this.lineCount = 20;
    this.createCode();
  }

  /**
   * 构造函数.
   * 
   * @param width 图片宽
   * @param height 图片高
   */
  public SCaptcha(int width, int height) {
    this.width = width;
    this.height = height;
    this.createCode();
  }

  /**
   * 构造函数.
   * 
   * @param width 图片宽
   * @param height 图片高
   * @param codeCount 字符个数
   * @param lineCount 干扰线条数
   */
  public SCaptcha(int width, int height, int codeCount, int lineCount) {
    this.width = width;
    this.height = height;
    this.codeCount = codeCount;
    this.lineCount = lineCount;
    this.createCode();
  }

  /**
   * 生成code有噪音.
   */
  public void createCode() {
    int x = 0;
    int fontHeight = 0;
    int codeY = 0;
    int red = 0;
    int green = 0;
    int blue = 0;

    x = width / (codeCount + 2);// 每个字符的宽度
    fontHeight = height - 2;// 字体的高度
    codeY = height - 4;

    // 图像buffer
    buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics2D g = buffImg.createGraphics();
    // 生成随机数
    Random random = new Random();
    // 将图像填充为白色
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, width, height);
    // 创建字体
    ImgFontByte imgFont = new ImgFontByte();
    Font font = imgFont.getFont(fontHeight);
    g.setFont(font);

    for (int i = 0; i < lineCount; i++) {
      int xs = random.nextInt(width);
      int ys = random.nextInt(height);
      final int xe = xs + random.nextInt(width / 8);
      final int ye = ys + random.nextInt(height / 8);
      red = random.nextInt(255);
      green = random.nextInt(255);
      blue = random.nextInt(255);
      g.setColor(new Color(red, green, blue));
      g.setStroke(new BasicStroke(2.0f));
      g.drawLine(xs, ys, xe, ye);
    }

    // randomCode记录随机产生的验证码
    StringBuffer randomCode = new StringBuffer();
    // 随机产生codeCount个字符的验证码。
    for (int i = 0; i < codeCount; i++) {
      final String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
      // 产生随机的颜色值，让输出的每个字符的颜色值都将不同。
      red = 0;// random.nextInt(255);
      green = 0;// random.nextInt(255);
      blue = 0;// random.nextInt(255);
      g.setColor(new Color(red, green, blue));
      g.drawString(strRand, (i + 1) * x, codeY);
      // 将产生的四个随机数组合在一起。
      randomCode.append(strRand);
    }
    // 将四位数字的验证码保存到Session中。
    code = randomCode.toString();
  }

  public void write(String path) throws IOException {
    OutputStream sos = new FileOutputStream(path);
    this.write(sos);
  }

  public void write(OutputStream sos) throws IOException {
    ImageIO.write(buffImg, "png", sos);
    sos.close();
  }

  public BufferedImage getBuffImg() {
    return buffImg;
  }

  public String getCode() {
    return code;
  }

  /**
   * ttf字体文件.
   * 
   * @author fred
   *
   */
  class ImgFontByte {
    public Font getFont(int fontHeight) {
      try {
        Font baseFont = Font.createFont(Font.TRUETYPE_FONT,
            new ByteArrayInputStream(hex2byte(getFontByteStr())));
        return baseFont.deriveFont(Font.PLAIN, fontHeight);
      } catch (Exception e) {
        return new Font("Arial", Font.PLAIN, fontHeight);
      }
    }

    private byte[] hex2byte(String str) {
      if (str == null) {
        return null;
      }
      str = str.trim();
      int len = str.length();
      if (len == 0 || len % 2 == 1) {
        return null;
      }

      byte[] b = new byte[len / 2];
      try {
        for (int i = 0; i < str.length(); i += 2) {
          b[i / 2] = (byte) Integer.decode("0x" + str.substring(i, i + 2)).intValue();
        }
        return b;
      } catch (Exception e) {
        return null;
      }
    }

    private String getFontByteStr() {
      return ConfigurationUtil.getProperty(ConfigurationUtil.FONT_STR);
    }
  }

  /**
   * 创建图片验证码.
   */
  public static String createImage(HttpServletRequest request, HttpServletResponse response) {
    // 设置响应的类型格式为图片格式
    response.setContentType("image/jpeg");
    // 禁止图像缓存。
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);

    HttpSession session = request.getSession();

    SCaptcha code = new SCaptcha(120, 40, 4, 10);
    session.setAttribute("verifyCode", code.getCode());
    try {
      code.write(response.getOutputStream());
      return "success";
    } catch (IOException e) {
      e.printStackTrace();
      return "error";
    }
  }

}
