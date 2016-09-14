package com.gigigo.multiplegridrecyclerview_demo;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

    public static ImageData generateRandomImageData(int width, int i) {
        return new ImageData("http://lorempixel.com/" + width + "/" + 300 + "/sports");
    }


    public static List<Object> generateRandomDataList(int width) {
        ArrayList<Object> data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            if (i < 5) {
                data.add(generateRandomImageData(width, i));
            } else if (i >= 5 && i <10) {
                data.add(generateRandomImageData(width+100, i));
            } else {
                data.add(generateRandomImageData(width-100, i));
            }
        }
        return data;
    }

    public static List<ImageData> generateRandomImageDataList(int width) {
        ArrayList<ImageData> data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            data.add(generateRandomImageData(width, i));
        }
        return data;
    }

}
