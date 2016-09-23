package com.gigigo.multiplegridrecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.gigigo.multiplegridrecyclerview.adapter.MultipleGridAdapter;
import com.gigigo.multiplegridrecyclerview.decoration.GridItemDividerDecoration;
import com.gigigo.multiplegridrecyclerview.entities.Widget;
import com.gigigo.multiplegridrecyclerview.layoutManager.SpannedGridLayoutManager;
import com.gigigo.multiplegridrecyclerview.viewholder.MultipleGridViewHolder;
import java.util.List;

public class MultipleGridRecyclerView extends FrameLayout {
  private View view;
  private View emptyViewLayout;
  private View recyclerViewLayout;

  private SwipeRefreshLayout swipeRefreshLayout;
  private RecyclerView recyclerView;
  private MultipleGridAdapter adapter;
  private RecyclerView.LayoutManager layoutManager;

  private int gridColumns = 3;
  private float cellAspectRatio = 0.77f;

  private OnRefreshListener refreshListener;

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

    view =
        LayoutInflater.from(getContext()).inflate(R.layout.multiple_grid_recycler_view, this, true);

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
    adapter = new MultipleGridAdapter(getContext());
  }

  private void initRecyclerView() {
    recyclerViewLayout = view.findViewById(R.id.recycler_view_layout);
    emptyViewLayout = view.findViewById(R.id.empty_view_layout);
    recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

    recyclerView.setAdapter(adapter);

    initMultipleGridLayoutManager();
    initItemDecoration();

    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
      }

      public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        int topRowVerticalPosition = (recyclerView == null || recyclerView.getChildCount() == 0) ? 0
            : recyclerView.getChildAt(0).getTop();
        swipeRefreshLayout.setEnabled(topRowVerticalPosition >= 0);
      }
    });
  }

  private void initRefreshLayout() {
    swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_recycler_view);
    swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
        android.R.color.holo_green_light, android.R.color.holo_orange_light,
        android.R.color.holo_red_light);

    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        refreshListener.onRefresh();
      }
    });
  }

  public void setAdapterDataViewHolder(Class valueClass,
      Class<? extends MultipleGridViewHolder> viewHolder) {
    adapter.bind(valueClass, viewHolder);
  }

  private void initMultipleGridLayoutManager() {
    configureLayoutManager(gridColumns, cellAspectRatio);

    recyclerView.setLayoutManager(layoutManager);

    setMultipleGridLayoutManager(layoutManager);
  }

  private void configureLayoutManager(final int gridColumns, float cellAspectRatio) {
    layoutManager = new SpannedGridLayoutManager(new SpannedGridLayoutManager.GridSpanLookup() {
      @Override public SpannedGridLayoutManager.SpanInfo getSpanInfo(int position) {
        Object element = adapter.getItem(position);

        int colSpan, rowSpan;
        if (element instanceof Widget) {
          colSpan = Math.max(((Widget) element).getColumn(), gridColumns);
          rowSpan = ((Widget) element).getRow();
        } else {
          colSpan = gridColumns;
          rowSpan = 1;
        }

        return new SpannedGridLayoutManager.SpanInfo(colSpan, rowSpan);
      }
    }, gridColumns, cellAspectRatio);
  }

  private void initItemDecoration() {
    recyclerView.addItemDecoration(new GridItemDividerDecoration(getContext(), R.dimen.divider_size,
        R.color.bg_empty_list_color));
  }

  public void setGridColumns(int gridColumns) {
    this.gridColumns = gridColumns;
    configureLayoutManager(gridColumns, cellAspectRatio);
    setMultipleGridLayoutManager(this.layoutManager);
  }

  public void setCellAspectRatio(float cellAspectRatio) {
    this.cellAspectRatio = cellAspectRatio;
    configureLayoutManager(gridColumns, cellAspectRatio);
    setMultipleGridLayoutManager(this.layoutManager);
  }

  public int getGridColumns() {
    return this.gridColumns;
  }

  public float getCellAspectRatio() {
    return this.cellAspectRatio;
  }

  private void setMultipleGridLayoutManager(RecyclerView.LayoutManager layoutManager) {
    recyclerView.setLayoutManager(layoutManager);
  }

  public void setRefreshing(boolean refreshing) {
    swipeRefreshLayout.setRefreshing(refreshing);
  }

  public void setOnRefreshListener(OnRefreshListener refreshListener) {
    this.refreshListener = refreshListener;
  }

  public void add(Object item) {
    adapter.add(item);
  }

  public void addAt(Object item, int position) {
    adapter.addAt(item, position);
  }

  public void loadData(List<?> data) {
    adapter.addAll(data);

    emptyViewLayout.setVisibility(GONE);
    recyclerViewLayout.setVisibility(VISIBLE);
  }

  public void addData(List<?> data) {
    adapter.append(data);

    emptyViewLayout.setVisibility(GONE);
    recyclerViewLayout.setVisibility(VISIBLE);
  }

  public boolean remove(Object item) {
    return adapter.remove(item);
  }

  public boolean removeAt(int position) {
    return adapter.removeAt(position);
  }

  public void clearData() {
    adapter.clear();

    recyclerViewLayout.setVisibility(GONE);
    emptyViewLayout.setVisibility(VISIBLE);
  }

  public interface OnRefreshListener {
    void onRefresh();
  }
}
