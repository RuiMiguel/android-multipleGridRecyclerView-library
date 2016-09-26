package com.ruialonso.multiplegridrecyclerview_demo;

import com.ruialonso.multiplegridrecyclerview_demo.recyclerview.CellImageWidget;
import com.ruialonso.multiplegridrecyclerview_demo.recyclerview.ImageWidget;
import com.ruialonso.multiplegridrecyclerview_demo.recyclerview.TextWidget;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {

  private static String[] categories = new String[]{"animals", "architecture", "nature", "people", "tech"};

  public static List<Object> generateRandomDataList(int num) {
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
    return new ImageWidget("http://placeimg.com/200/100/"+getCategory());
  }

  public static CellImageWidget generateRandomCellImageData(int column, int row) {
    CellImageWidget imageWidget = new CellImageWidget();
    imageWidget.setData(new ImageWidget("http://placeimg.com/200/100/"+getCategory()));
    imageWidget.setColumn(column);
    imageWidget.setRow(row);
    return imageWidget;
  }

  public static TextWidget generateRandomTextData(int index) {
    return new TextWidget("item" + index);
  }

  private static String getCategory() {
    int random = new Random().nextInt();
    int index = Math.abs(random)%(categories.length-1);
    return categories[index];
  }
}
