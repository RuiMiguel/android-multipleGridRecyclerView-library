package com.gigigo.multiplegridrecyclerview.viewholder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyViewHolder;

/**
 * Created by rui.alonso on 15/9/16.
 */
public abstract class MultipleGridViewHolder<V> extends EasyViewHolder<V> {
  public MultipleGridViewHolder(Context context, ViewGroup parent, int layoutId) {
    super(context, parent, layoutId);
  }
}
