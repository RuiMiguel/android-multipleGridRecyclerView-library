package com.gigigo.multiplegridrecyclerview.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class MultipleGridViewHolder<V> extends RecyclerView.ViewHolder
    implements View.OnLongClickListener, View.OnClickListener, View.OnDragListener {

  private MultipleGridViewHolder.OnItemClickListener itemClickListener;
  private MultipleGridViewHolder.OnItemLongClickListener itemLongClickListener;
  private MultipleGridViewHolder.OnItemDragListener itemDragListener;

  public MultipleGridViewHolder(Context context, ViewGroup parent, int layoutId) {
    super(LayoutInflater.from(context).inflate(layoutId, parent, false));
    bindListeners();
  }

  public MultipleGridViewHolder(View itemView) {
    super(itemView);
  }

  private void bindListeners() {
    itemView.setOnClickListener(this);
    itemView.setOnLongClickListener(this);
    itemView.setOnDragListener(this);
  }

  public void setItemClickListener(MultipleGridViewHolder.OnItemClickListener itemClickListener) {
    this.itemClickListener = itemClickListener;
  }

  public void setItemLongClickListener(
      MultipleGridViewHolder.OnItemLongClickListener itemLongClickListener) {
    this.itemLongClickListener = itemLongClickListener;
  }

  public void setItemDragListener(MultipleGridViewHolder.OnItemDragListener itemDragClickListener) {
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
