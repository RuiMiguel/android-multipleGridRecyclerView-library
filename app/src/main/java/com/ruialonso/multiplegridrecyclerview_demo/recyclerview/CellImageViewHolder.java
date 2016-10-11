package com.ruialonso.multiplegridrecyclerview_demo.recyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.gigigo.baserecycleradapter.viewholder.BaseViewHolder;
import com.gigigo.ui.imageloader.ImageLoader;
import com.gigigo.ui.imageloader_glide.GlideImageLoaderImp;
import com.ruialonso.multiplegridrecyclerview_demo.R;

public class CellImageViewHolder extends BaseViewHolder<CellImageWidget> {

  private ImageLoader imageLoader;
  private ImageView imageView;

  public CellImageViewHolder(Context context, ViewGroup parent) {
    super(context, parent, R.layout.content_item_image_element);

    RequestManager requestManager = Glide.with(parent.getContext());
    this.imageLoader = new GlideImageLoaderImp(requestManager);
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
    imageLoader.load(item.getData().getImageUrl(), imageView, R.drawable.placeholder);
  }
}
