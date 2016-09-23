package com.ruialonso.multiplegridrecyclerview_demo.recyclerview;

import com.ruialonso.multiplegridrecyclerview.entities.Widget;

public class CellImageWidget extends Widget<ImageWidget> {
  private String imageUrl;

  public CellImageWidget() {
  }

  public CellImageWidget(String imageUrl) {
    super();
    this.imageUrl = imageUrl;
  }

  public String getImageUrl() {
    return imageUrl;
  }
}
