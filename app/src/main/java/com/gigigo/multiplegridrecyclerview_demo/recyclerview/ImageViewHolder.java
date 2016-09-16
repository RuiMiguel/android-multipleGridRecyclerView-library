package com.gigigo.multiplegridrecyclerview_demo.recyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyViewHolder;
import com.gigigo.multiplegridrecyclerview_demo.R;
import com.squareup.picasso.Picasso;

public class ImageViewHolder extends EasyViewHolder<Widget> {

  private final Picasso picasso;
  private ImageView image;

  public ImageViewHolder(Context context, ViewGroup parent) {
    this(context, parent, Picasso.with(context));
  }

  public ImageViewHolder(Context context, ViewGroup parent, Picasso picasso) {
    super(context, parent, R.layout.content_item_element);
    this.picasso = picasso;
    image = (ImageView) this.itemView.findViewById(R.id.image);

    itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked position: " + getLayoutPosition(),
            Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override public void bindTo(Widget item) {
    picasso.load(item.getImageUrl()).placeholder(R.drawable.placeholder).into(image);
  }
}
