package com.gigigo.multiplegridrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import com.carlosdelachica.easyrecycleradapters.adapter.BaseEasyViewHolderFactory;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyRecyclerAdapter;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyViewHolder;
import com.gigigo.multiplegridrecyclerview.R;

/**
 * Created by rui.alonso on 15/9/16.
 */
public class MultipleGridAdapter extends EasyRecyclerAdapter {

  public MultipleGridAdapter(Context context, Class valueClass,
      Class<? extends EasyViewHolder> easyViewHolderClass) {
    super(context, valueClass, easyViewHolderClass);
  }

  public MultipleGridAdapter(Context context) {
    super(context);
  }

  public MultipleGridAdapter(BaseEasyViewHolderFactory easyViewHolderFactory, Class valueClass,
      Class<? extends EasyViewHolder> easyViewHolderClass) {
    super(easyViewHolderFactory, valueClass, easyViewHolderClass);
  }

  public MultipleGridAdapter(BaseEasyViewHolderFactory easyViewHolderFactory) {
    super(easyViewHolderFactory);
  }

  @Override public int getItemViewType(int position) {
    return super.getItemViewType(position);
  }

  @Override public EasyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return super.onCreateViewHolder(parent, viewType);
  }

  /*
  @Override public EasyViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
    final View
        view = LayoutInflater.from(context).inflate(R.layout.content_item_element, parent, false);

    view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
      @Override public boolean onPreDraw() {
        GridType gridType = GridType.values()[viewType];

        final ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
          StaggeredGridLayoutManager.LayoutParams staggeredLayoutParams = (StaggeredGridLayoutManager.LayoutParams) layoutParams;

          switch (gridType) {
            case SUPERHIGHLIGHTED_3x3:
              staggeredLayoutParams.width = recyclerViewWidth;
              staggeredLayoutParams.height = recyclerViewWidth;
              staggeredLayoutParams.setFullSpan(true);
              break;
            case HIGHLIGHTED_2x2:
              staggeredLayoutParams.width = 2 * recyclerViewWidth / columnNumber;
              staggeredLayoutParams.height = 2 * recyclerViewWidth / columnNumber;
              staggeredLayoutParams.setFullSpan(false);
              break;
            case HORIZONTAL_3x1:
              staggeredLayoutParams.width = recyclerViewWidth;
              staggeredLayoutParams.height = recyclerViewWidth / columnNumber;
              staggeredLayoutParams.setFullSpan(true);
              break;
            case HORIZONTAL2x1:
              staggeredLayoutParams.width = 2 * recyclerViewWidth / columnNumber;
              staggeredLayoutParams.height = recyclerViewWidth / columnNumber;
              staggeredLayoutParams.setFullSpan(false);
              break;
            case VERTICAL_1x1:
            default:
              staggeredLayoutParams.width = recyclerViewWidth / columnNumber;
              staggeredLayoutParams.height = recyclerViewWidth / columnNumber;
              staggeredLayoutParams.setFullSpan(false);
              break;
          }

          view.setLayoutParams(staggeredLayoutParams);
          final StaggeredGridLayoutManager staggeredGridLayoutManager =
              (StaggeredGridLayoutManager) ((RecyclerView) parent).getLayoutManager();
          staggeredGridLayoutManager.invalidateSpanAssignments();
        }

        view.getViewTreeObserver().removeOnPreDrawListener(this);

        return true;
      }
    });

    MultiGridViewHolder viewHolder = new MultiGridViewHolder(view);

    return viewHolder;
  }
  */
}
