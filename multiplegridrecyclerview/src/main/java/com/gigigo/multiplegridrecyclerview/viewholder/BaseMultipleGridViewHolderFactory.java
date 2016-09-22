package com.gigigo.multiplegridrecyclerview.viewholder;

import android.content.Context;
import android.view.ViewGroup;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class BaseMultipleGridViewHolderFactory {
  protected Context context;

  private Map<Class, Class<? extends MultipleGridViewHolder>> boundViewHolders = new HashMap<>();

  public BaseMultipleGridViewHolderFactory(Context context) {
    this.context = context;
  }

  public MultipleGridViewHolder create(Class valueClass, ViewGroup parent) {
    try {
      Class<? extends MultipleGridViewHolder> viewHolderClass = boundViewHolders.get(valueClass);
      Constructor<? extends MultipleGridViewHolder> constructor =
          viewHolderClass.getDeclaredConstructor(Context.class, ViewGroup.class);
      return constructor.newInstance(context, parent);
    } catch (Throwable ex) {
      throw new RuntimeException(
          "Unable to create ViewHolder for" + valueClass + ". " + ex.getCause().getMessage(), ex);
    }
  }

  public void bind(Class valueClass, Class<? extends MultipleGridViewHolder> viewHolderClass) {
    boundViewHolders.put(valueClass, viewHolderClass);
  }
}
