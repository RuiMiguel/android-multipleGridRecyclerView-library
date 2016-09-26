package com.ruialonso.multiplegridrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.ruialonso.multiplegridrecyclerview.viewholder.BaseMultipleGridViewHolderFactory;
import com.ruialonso.multiplegridrecyclerview.viewholder.MultipleGridViewHolder;
import java.util.ArrayList;
import java.util.List;

public class MultipleGridAdapter<V> extends RecyclerView.Adapter<MultipleGridViewHolder> {

  private BaseMultipleGridViewHolderFactory viewHolderFactory;
  private List<Class> valueClassTypes = new ArrayList<>();

  private List<V> data = new ArrayList<>();

  private MultipleGridViewHolder.OnItemClickListener itemClickListener;
  private MultipleGridViewHolder.OnItemLongClickListener itemLongClickListener;
  private MultipleGridViewHolder.OnItemDragListener itemDragListener;

  public MultipleGridAdapter(Context context, Class valueClass,
      Class<? extends MultipleGridViewHolder> viewHolderClass) {
    this(context);
    bind(valueClass, viewHolderClass);
  }

  public MultipleGridAdapter(Context context) {
    this(new BaseMultipleGridViewHolderFactory(context));
  }

  public MultipleGridAdapter(BaseMultipleGridViewHolderFactory baseMultipleGridViewHolderFactory,
      Class valueClass, Class<? extends MultipleGridViewHolder> viewHolderClass) {
    this(baseMultipleGridViewHolderFactory);
    bind(valueClass, viewHolderClass);
  }

  public MultipleGridAdapter(BaseMultipleGridViewHolderFactory baseMultipleGridViewHolderFactory) {
    this.viewHolderFactory = baseMultipleGridViewHolderFactory;
  }

  public void bind(Class valueClass, Class<? extends MultipleGridViewHolder> viewHolderClass) {
    valueClassTypes.add(valueClass);
    viewHolderFactory.bind(valueClass, viewHolderClass);
  }

  @Override public MultipleGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    MultipleGridViewHolder viewHolder =
        viewHolderFactory.create(valueClassTypes.get(viewType), parent);
    bindListeners(viewHolder);
    return viewHolder;
  }

  private void bindListeners(MultipleGridViewHolder viewHolder) {
    if (viewHolder != null) {
      viewHolder.setItemClickListener(itemClickListener);
      viewHolder.setItemLongClickListener(itemLongClickListener);
      viewHolder.setItemDragListener(itemDragListener);
    }
  }

  public void setItemClickListener(MultipleGridViewHolder.OnItemClickListener itemClickListener) {
    this.itemClickListener = itemClickListener;
  }

  public void setItemLongClickListener(MultipleGridViewHolder.OnItemLongClickListener itemLongClickListener) {
    this.itemLongClickListener = itemLongClickListener;
  }

  public void setItemDragListener(MultipleGridViewHolder.OnItemDragListener itemDragListener) {
    this.itemDragListener = itemDragListener;
  }

  @Override public void onBindViewHolder(MultipleGridViewHolder holder, int position) {
    holder.bindTo(data.get(position), position);
  }

  @Override public int getItemViewType(int position) {
    return valueClassTypes.indexOf(data.get(position).getClass());
  }

  public void add(V item) {
    data.add(item);
    notifyDataSetChanged();
  }

  public void addAt(V item, int position) {
    boolean validIndex = isValidIndex(position);
    if (validIndex) {
      data.add(position, item);
      notifyItemInserted(position);
    }
  }

  public void addAll(List<V> items) {
    data.clear();
    append(items);
  }

  public void append(List<V> items) {
    data.addAll(items);
    notifyDataSetChanged();
  }

  public boolean remove(V item) {
    int position = getIndex(item);
    return removeAt(position);
  }

  public boolean removeAt(int position) {
    boolean validIndex = isValidIndex(position);
    if (validIndex) {
      data.remove(position);
      notifyItemRemoved(position);
    }
    return validIndex;
  }

  public void clear() {
    data.clear();
    notifyDataSetChanged();
  }

  private boolean isValidIndex(int position) {
    return (position >= 0 && position < getItemCount());
  }

  public int getIndex(V item) {
    return data.indexOf(item);
  }

  public V getItem(int position) {
    return data.get(position);
  }

  @Override public int getItemCount() {
    return data.size();
  }
}
