package com.gigigo.multiplegridrecyclerview_demo;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

  public static ImageData generateRandomImageData(int width, int i) {
    return new ImageData("http://placeimg.com/" + width + "/" + 1 + "/any");
  }

  public static List<Object> generateRandomDataList(int num, int width) {
    ArrayList<Object> data = new ArrayList<>();
    for (int i = 0; i < num; i++) {
      if (i % 2 == 0) {
        data.add(generateRandomImageData(width, i));
      } else if (i % 2 == 1) {
        data.add(generateRandomImageData(width + 100, i));
      } else {
        data.add(generateRandomImageData(width - 100, i));
      }
    }
    return data;
  }
}
