package com.gigigo.multiplegridrecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyRecyclerAdapter;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyViewHolder;
import com.carlosdelachica.easyrecycleradapters.decorations.DividerItemDecoration;
import com.carlosdelachica.easyrecycleradapters.recycler_view_manager.EasyRecyclerViewManager;
import java.util.List;

/**
 * TODO: document your custom view class.
 */
public class MultipleGridRecyclerView extends FrameLayout {
  private View emptyViewLayout;
  private View recyclerViewLayout;

  private SwipeRefreshLayout swipeRefreshLayout;
  private RecyclerView recyclerView;
  private EasyRecyclerAdapter adapter;

  private EasyRecyclerViewManager easyRecyclerViewManager;
  private OnRefreshListener refreshListener;
  private View view;

  public MultipleGridRecyclerView(Context context) {
    super(context);
    init(null, 0);
  }

  public MultipleGridRecyclerView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(attrs, 0);
  }

  public MultipleGridRecyclerView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init(attrs, defStyle);
  }

  private void init(AttributeSet attrs, int defStyle) {
    loadAttributes(attrs, defStyle);

    view = LayoutInflater.from(getContext()).inflate(R.layout.multiple_grid_recycler_view, this, true);

    initAdapter();
    initRecyclerView();
    initRefreshLayout();
  }

  private void loadAttributes(AttributeSet attrs, int defStyle) {
    final TypedArray a =
        getContext().obtainStyledAttributes(attrs, R.styleable.MultipleGridRecyclerView, defStyle,
            0);
  }

  private void initAdapter() {
    adapter = new EasyRecyclerAdapter(getContext());
  }

  private void initRecyclerView() {
    recyclerViewLayout = view.findViewById(R.id.recycler_view_layout);
    emptyViewLayout = view.findViewById(R.id.empty_view_layout);
    recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

    setMultipleGridLayoutManager(getResources().getInteger(R.integer.grid_columns));

    recyclerView.setAdapter(adapter);
    recyclerView.addItemDecoration(new DividerItemDecoration(getContext()));

    recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
      public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
      }

      public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        int topRowVerticalPosition = (recyclerView == null || recyclerView.getChildCount() == 0) ? 0
            : recyclerView.getChildAt(0).getTop();
        if(swipeRefreshLayout != null) swipeRefreshLayout.setEnabled(topRowVerticalPosition >= 0);
      }
    });
  }

  private void initRefreshLayout() {
    swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_recycler_view);
    swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
        android.R.color.holo_orange_light, android.R.color.holo_red_light);

    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        refreshListener.onRefresh();
        //easyRecyclerViewManager.onRefresh();
      }
    });
  }

  public void setAdapterDataViewHolder(Class valueClass,
      Class<? extends EasyViewHolder> viewHolder) {
    adapter.bind(valueClass, viewHolder);
/*
    easyRecyclerViewManager = new EasyRecyclerViewManager.Builder(recyclerView, adapter)
        .layoutManager(new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.grid_columns)))
        .divider(R.drawable.custom_divider)
        .emptyLoadingListTextView(emptyList)
        .emptyListText(R.string.empty_list)
        .emptyListTextColor(R.color.accentColor)
        .loadingView(loadingView)
        .clickListener(this)
        .longClickListener(this)
        .recyclerViewClipToPadding(false)
        .recyclerViewHasFixedSize(true)
        .build();
        */
  }

  public void setMultipleGridLayoutManager(int gridColumns) {
    LinearLayoutManager layoutManager = new GridLayoutManager(getContext(), gridColumns);
    recyclerView.setLayoutManager(layoutManager);
  }


  public void setRefreshing(boolean refreshing) {
    swipeRefreshLayout.setRefreshing(refreshing);
  }

  public void setOnRefreshListener(OnRefreshListener refreshListener) {
    this.refreshListener = refreshListener;
  }


  public void loadData(List<Object> data) {
    adapter.clear();
    adapter.addAll(data);
    adapter.notifyDataSetChanged();

    emptyViewLayout.setVisibility(GONE);
    recyclerViewLayout.setVisibility(VISIBLE);
  }

  public void addData(List<Object> data) {
    //easyRecyclerViewManager.addAll(data);
    adapter.appendAll(data);
    adapter.notifyItemRangeInserted(adapter.getItemCount(), data.size());

    emptyViewLayout.setVisibility(GONE);
    recyclerViewLayout.setVisibility(VISIBLE);
  }

  public void clearData() {
    adapter.clear();
    adapter.notifyItemRangeRemoved(0, adapter.getItemCount());
    recyclerViewLayout.setVisibility(GONE);
    emptyViewLayout.setVisibility(VISIBLE);
  }
}
