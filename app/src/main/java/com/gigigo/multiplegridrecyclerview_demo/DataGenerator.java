package com.gigigo.multiplegridrecyclerview_demo;

import com.gigigo.multiplegridrecyclerview_demo.recyclerview.ImageWidget;
import com.gigigo.multiplegridrecyclerview_demo.recyclerview.TextWidget;
import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

  public static List<Object> generateRandomImageDataList(int num, int width) {
    ArrayList<Object> data = new ArrayList<>();
    for (int i = 0; i < num; i++) {
      ImageWidget element;

      if (i % 2 == 0) {
        element = new ImageWidget("http://placeimg.com/" + width + "/" + 100 + "/any");
      } else if (i % 2 == 1) {
        element = new ImageWidget("http://placeimg.com/" + (width + 100) + "/" + 100 + "/any");
      } else {
        element = new ImageWidget("http://placeimg.com/" + (width - 100) + "/" + 100 + "/any");
      }

      data.add(element);
    }
    return data;
  }

  public static List<Object> generateRandomTextDataList(int num, int width) {
    ArrayList<Object> data = new ArrayList<>();
    for (int i = 0; i < num; i++) {
      TextWidget element = new TextWidget("item" + i);
      data.add(element);
    }
    return data;
  }
}
