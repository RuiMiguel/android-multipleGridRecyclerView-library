package com.gigigo.ui.imageloader;

import android.widget.ImageView;
import java.util.Map;

/**
 * Created by rui.alonso on 20/9/16.
 */
public interface ImageLoader {
  void load(String url, ImageView imageView);

  void load(int resourceId, ImageView imageView);

  void load(String url, ImageView imageView, int placeholder);

  void loadCircleImage(String url, ImageView imageView);

  void loadCircleImage(int resourceId, ImageView imageView);

  void loadCircleImage(String url, ImageView imageView, int placeholder);

  void loadCircleImage(String url, Map<String, String> params, ImageView imageView,
      int placeholder);
}
