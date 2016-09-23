package com.gigigo.multiplegridrecyclerview_demo;

import com.gigigo.multiplegridrecyclerview_demo.recyclerview.ImageWidget;
import com.gigigo.multiplegridrecyclerview_demo.recyclerview.TextWidget;
import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

  public static List<Object> generateRandomDataList(int num, int width) {
    ArrayList<Object> data = new ArrayList<>();
    for (int i = 0; i < num; i++) {
      if (i % 2 == 0) {
        data.add(generateRandomImageData(width));
      } else {
        data.add(generateRandomTextData(i));
      }
    }
    return data;
  }

  public static ImageWidget generateRandomImageData(int width) {
    return new ImageWidget("http://placeimg.com/" + width + "/" + 100 + "/any");
  }

  public static TextWidget generateRandomTextData(int index) {
    return new TextWidget("item" + index);
  }
}
