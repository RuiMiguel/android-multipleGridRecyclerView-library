package com.gigigo.multiplegridrecyclerview_demo.recyclerview;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyViewHolder;
import com.gigigo.multiplegridrecyclerview_demo.R;
import com.squareup.picasso.Picasso;

public class ImageViewHolder extends EasyViewHolder<ImageData> {

  private final Picasso picasso;
  private ImageView image;

  public ImageViewHolder(Context context, ViewGroup parent) {
    this(context, parent, Picasso.with(context));
  }

  public ImageViewHolder(Context context, ViewGroup parent, Picasso picasso) {
    super(context, parent, R.layout.image_item);
    this.picasso = picasso;
    image = (ImageView) this.itemView.findViewById(R.id.image);
  }

  @Override public void bindTo(ImageData item) {
    picasso.load(item.getImageUrl()).placeholder(R.drawable.placeholder).into(image);
  }
}
