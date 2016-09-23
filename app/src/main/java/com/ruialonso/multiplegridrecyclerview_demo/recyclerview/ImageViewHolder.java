package com.ruialonso.multiplegridrecyclerview_demo.recyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.ruialonso.multiplegridrecyclerview.viewholder.MultipleGridViewHolder;
import com.ruialonso.multiplegridrecyclerview_demo.R;
import com.squareup.picasso.Picasso;

public class ImageViewHolder extends MultipleGridViewHolder<ImageWidget> {

  private final Picasso picasso;
  private ImageView imageView;

  public ImageViewHolder(Context context, ViewGroup parent) {
    this(context, parent, Picasso.with(context));
  }

  public ImageViewHolder(Context context, ViewGroup parent, Picasso picasso) {
    super(context, parent, R.layout.content_item_image_element);
    this.picasso = picasso;
    imageView = (ImageView) this.itemView.findViewById(R.id.image_view);

    bindListeners();
  }

  public void bindListeners() {
    itemView.setOnClickListener(this);
    itemView.setOnLongClickListener(new View.OnLongClickListener() {
      @Override public boolean onLongClick(View view) {
        Toast.makeText(view.getContext(), "Long clicked from viewHolder - position: " + getLayoutPosition(),
            Toast.LENGTH_SHORT).show();
        return false;
      }
    });
  }

  @Override public void bindTo(ImageWidget item, int position) {
    picasso.load(item.getImageUrl()).placeholder(R.drawable.placeholder).into(imageView);
  }
}
