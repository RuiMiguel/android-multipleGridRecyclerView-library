package com.gigigo.baserecycleradapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseViewHolder<V> extends RecyclerView.ViewHolder
    implements View.OnLongClickListener, View.OnClickListener, View.OnDragListener {

  private BaseViewHolder.OnItemClickListener itemClickListener;
  private BaseViewHolder.OnItemLongClickListener itemLongClickListener;
  private BaseViewHolder.OnItemDragListener itemDragListener;

  public BaseViewHolder(Context context, ViewGroup parent, int layoutId) {
    super(LayoutInflater.from(context).inflate(layoutId, parent, false));
    bindListeners();
  }

  public BaseViewHolder(View itemView) {
    super(itemView);
  }

  private void bindListeners() {
    itemView.setOnClickListener(this);
    itemView.setOnLongClickListener(this);
    itemView.setOnDragListener(this);
  }

  public void setItemClickListener(BaseViewHolder.OnItemClickListener itemClickListener) {
    this.itemClickListener = itemClickListener;
  }

  public void setItemLongClickListener(
      BaseViewHolder.OnItemLongClickListener itemLongClickListener) {
    this.itemLongClickListener = itemLongClickListener;
  }

  public void setItemDragListener(BaseViewHolder.OnItemDragListener itemDragClickListener) {
    this.itemDragListener = itemDragClickListener;
  }

  @Override public void onClick(View v) {
    if (itemClickListener == null) return;
    itemClickListener.onItemClick(getLayoutPosition(), v);
  }

  @Override public boolean onLongClick(View v) {
    if (itemLongClickListener == null) {
      return false;
    }
    return itemLongClickListener.onItemLongClicked(getLayoutPosition(), itemView);
  }

  @Override public boolean onDrag(View v, DragEvent event) {
    if (itemDragListener == null) {
      return false;
    }
    return itemDragListener.OnItemDragged(getLayoutPosition(), v);
  }

  public abstract void bindTo(V value, int position);

  public interface OnItemClickListener {
    void onItemClick(final int position, View view);
  }

  public interface OnItemLongClickListener {
    boolean onItemLongClicked(final int position, View view);
  }

  public interface OnItemDragListener {
    boolean OnItemDragged(final int position, View view);
  }
}
