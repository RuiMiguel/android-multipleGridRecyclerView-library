package com.gigigo.baserecycleradapter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.gigigo.baserecycleradapter.viewholder.BaseViewHolder;
import com.gigigo.baserecycleradapter.viewholder.BaseViewHolderFactory;
import java.util.ArrayList;
import java.util.List;

public class BaseRecyclerAdapter<V> extends RecyclerView.Adapter<BaseViewHolder> {

  private BaseViewHolderFactory viewHolderFactory;
  private List<Class> valueClassTypes = new ArrayList<>();

  private List<V> data = new ArrayList<>();

  private BaseViewHolder.OnItemClickListener itemClickListener;
  private BaseViewHolder.OnItemLongClickListener itemLongClickListener;
  private BaseViewHolder.OnItemDragListener itemDragListener;

  public BaseRecyclerAdapter(Context context, Class valueClass,
      Class<? extends BaseViewHolder> viewHolderClass) {
    this(context);
    bind(valueClass, viewHolderClass);
  }

  public BaseRecyclerAdapter(Context context) {
    this(new BaseViewHolderFactory(context));
  }

  public BaseRecyclerAdapter(BaseViewHolderFactory baseBaseViewHolderFactory, Class valueClass,
      Class<? extends BaseViewHolder> viewHolderClass) {
    this(baseBaseViewHolderFactory);
    bind(valueClass, viewHolderClass);
  }

  public BaseRecyclerAdapter(BaseViewHolderFactory baseBaseViewHolderFactory) {
    this.viewHolderFactory = baseBaseViewHolderFactory;
  }

  public void bind(Class valueClass, Class<? extends BaseViewHolder> viewHolderClass) {
    valueClassTypes.add(valueClass);
    viewHolderFactory.bind(valueClass, viewHolderClass);
  }

  @Override public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    BaseViewHolder viewHolder = viewHolderFactory.create(valueClassTypes.get(viewType), parent);
    bindListeners(viewHolder);
    return viewHolder;
  }

  private void bindListeners(BaseViewHolder viewHolder) {
    if (viewHolder != null) {
      viewHolder.setItemClickListener(itemClickListener);
      viewHolder.setItemLongClickListener(itemLongClickListener);
      viewHolder.setItemDragListener(itemDragListener);
    }
  }

  public void setItemClickListener(BaseViewHolder.OnItemClickListener itemClickListener) {
    this.itemClickListener = itemClickListener;
  }

  public void setItemLongClickListener(
      BaseViewHolder.OnItemLongClickListener itemLongClickListener) {
    this.itemLongClickListener = itemLongClickListener;
  }

  public void setItemDragListener(BaseViewHolder.OnItemDragListener itemDragListener) {
    this.itemDragListener = itemDragListener;
  }

  @Override public void onBindViewHolder(BaseViewHolder holder, int position) {
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
