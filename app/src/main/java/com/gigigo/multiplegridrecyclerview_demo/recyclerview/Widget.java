package com.gigigo.multiplegridrecyclerview_demo.recyclerview;

public class Widget {
  private String text;
  private String imageUrl;
  private int width;
  private int heigth;

  public Widget(String text, String imageUrl, int width, int heigth) {
    this.text = text;
    this.imageUrl = imageUrl;
    this.width = width;
    this.heigth = heigth;
  }

  public String getText() {
    return text;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public int getWidth() {
    return width;
  }

  public int getHeigth() {
    return heigth;
  }
}
