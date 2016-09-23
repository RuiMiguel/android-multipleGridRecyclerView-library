package com.gigigo.multiplegridrecyclerview_demo;

import com.gigigo.multiplegridrecyclerview.entities.Widget;
import com.gigigo.multiplegridrecyclerview_demo.recyclerview.CellImageWidget;
import com.gigigo.multiplegridrecyclerview_demo.recyclerview.ImageWidget;
import com.gigigo.multiplegridrecyclerview_demo.recyclerview.TextWidget;
import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

  public static List<Object> generateRandomDataList(int num, int width) {
    ArrayList<Object> data = new ArrayList<>();
    for (int i = 0; i < num; i++) {
      if (i % 2 == 0) {
        data.add(generateRandomImageData());
      } else {
        data.add(generateRandomTextData(i));
      }
    }
    return data;
  }

  public static ImageWidget generateRandomImageData() {
    return new ImageWidget("http://placeimg.com/200/100/any");
  }

  public static CellImageWidget generateRandomCellImageData(int column, int row) {
    CellImageWidget imageWidget = new CellImageWidget();
    imageWidget.setData(new ImageWidget("http://placeimg.com/200/100/any"));
    imageWidget.setColumn(column);
    imageWidget.setRow(row);
    return imageWidget;
  }

  public static TextWidget generateRandomTextData(int index) {
    return new TextWidget("item" + index);
  }
}
