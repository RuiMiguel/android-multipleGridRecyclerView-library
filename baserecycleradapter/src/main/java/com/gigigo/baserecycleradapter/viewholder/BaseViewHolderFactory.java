package com.gigigo.baserecycleradapter.viewholder;

import android.content.Context;
import android.view.ViewGroup;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class BaseViewHolderFactory {
  protected Context context;

  private Map<Class, Class<? extends BaseViewHolder>> boundViewHolders = new HashMap<>();

  public BaseViewHolderFactory(Context context) {
    this.context = context;
  }

  public BaseViewHolder create(Class valueClass, ViewGroup parent) {
    try {
      Class<? extends BaseViewHolder> viewHolderClass = boundViewHolders.get(valueClass);
      Constructor<? extends BaseViewHolder> constructor =
          viewHolderClass.getDeclaredConstructor(Context.class, ViewGroup.class);
      return constructor.newInstance(context, parent);
    } catch (Throwable ex) {
      throw new RuntimeException(
          "Unable to create ViewHolder for" + valueClass + ". " + ex.getCause().getMessage(), ex);
    }
  }

  public void bind(Class valueClass, Class<? extends BaseViewHolder> viewHolderClass) {
    boundViewHolders.put(valueClass, viewHolderClass);
  }
}
