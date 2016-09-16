package com.gigigo.multiplegridrecyclerview_demo;

import com.gigigo.multiplegridrecyclerview_demo.recyclerview.Widget;
import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

  public static List<Object> generateRandomDataList(int num, int width) {
    ArrayList<Object> data = new ArrayList<>();
    for (int i = 0; i < num; i++) {
      Widget element;
      if (i % 2 == 0) {
        element = new Widget("item" + i, "http://placeimg.com/" + width + "/" + 100 + "/any", 2, 1);
      } else if (i % 2 == 1) {
        element =
            new Widget("item" + i, "http://placeimg.com/" + (width + 100) + "/" + 100 + "/any", 2,
                1);
      } else {
        element =
            new Widget("item" + i, "http://placeimg.com/" + (width - 100) + "/" + 100 + "/any", 2,
                1);
      }

      data.add(element);
    }
    return data;
  }
}
