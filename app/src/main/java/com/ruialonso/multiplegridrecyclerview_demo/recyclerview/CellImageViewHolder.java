package com.ruialonso.multiplegridrecyclerview_demo.recyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.ruialonso.multiplegridrecyclerview.viewholder.MultipleGridViewHolder;
import com.ruialonso.multiplegridrecyclerview_demo.R;
import com.squareup.picasso.Picasso;

public class CellImageViewHolder extends MultipleGridViewHolder<CellImageWidget> {

  private final Picasso picasso;
  private ImageView imageView;

  public CellImageViewHolder(Context context, ViewGroup parent) {
    this(context, parent, Picasso.with(context));
  }

  public CellImageViewHolder(Context context, ViewGroup parent, Picasso picasso) {
    super(context, parent, R.layout.content_item_image_element);
    this.picasso = picasso;
    imageView = (ImageView) this.itemView.findViewById(R.id.image_view);

    bindListeners();
  }

  public void bindListeners() {
    setItemClickListener(new OnItemClickListener() {
      @Override public void onItemClick(int position, View view) {
        Toast.makeText(view.getContext(), "Clicked position: " + getLayoutPosition(),
            Toast.LENGTH_SHORT).show();
      }
    });
    setItemLongClickListener(new OnItemLongClickListener() {
      @Override public boolean onItemLongClicked(int position, View view) {
        Toast.makeText(view.getContext(), "Long clicked position: " + getLayoutPosition(),
            Toast.LENGTH_SHORT).show();
        return false;
      }
    });
    setItemDragListener(new OnItemDragListener() {
      @Override public boolean OnItemDragged(int position, View view) {
        Toast.makeText(view.getContext(), "Dragged position: " + getLayoutPosition(),
            Toast.LENGTH_SHORT).show();
        return false;
      }
    });
  }

  @Override public void bindTo(CellImageWidget item, int position) {
    picasso.load(item.getImageUrl()).placeholder(R.drawable.placeholder).into(imageView);
  }
}
