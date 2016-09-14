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
    View view =
        LayoutInflater.from(getContext()).inflate(R.layout.multiple_grid_recycler_view, this, true);
    emptyViewLayout = view.findViewById(R.id.empty_view_layout);
    recyclerViewLayout = view.findViewById(R.id.recycler_view_layout);

    swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_recycler_view);
    swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
        android.R.color.holo_orange_light, android.R.color.holo_red_light);

    recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

    initAdapter();
    initRecyclerView();
    initRefreshLayout();

    // Load attributes
    final TypedArray a =
        getContext().obtainStyledAttributes(attrs, R.styleable.MultipleGridRecyclerView, defStyle,
            0);

    /*
    mExampleString = a.getString(R.styleable.MultipleGridRecyclerView_exampleString);
    mExampleColor = a.getColor(R.styleable.MultipleGridRecyclerView_exampleColor, mExampleColor);
    // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
    // values that should fall on pixel boundaries.
    mExampleDimension =
        a.getDimension(R.styleable.MultipleGridRecyclerView_exampleDimension, mExampleDimension);

    if (a.hasValue(R.styleable.MultipleGridRecyclerView_exampleDrawable)) {
      mExampleDrawable = a.getDrawable(R.styleable.MultipleGridRecyclerView_exampleDrawable);
      mExampleDrawable.setCallback(this);
    }


    a.recycle();

    // Set up a default TextPaint object
    mTextPaint = new TextPaint();
    mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
    mTextPaint.setTextAlign(Paint.Align.LEFT);

    // Update TextPaint and text measurements from attributes
    invalidateTextPaintAndMeasurements();
    */
  }

  private void initAdapter() {
    adapter = new EasyRecyclerAdapter(getContext());
  }

  private void initRecyclerView() {
    setMultipleGridLayoutManager(getResources().getInteger(R.integer.grid_columns));

    recyclerView.setAdapter(adapter);
    recyclerView.addItemDecoration(new DividerItemDecoration(getContext()));
  }

  private void initRefreshLayout() {
    recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
      public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
      }

      public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        int topRowVerticalPosition = (recyclerView == null || recyclerView.getChildCount() == 0) ? 0
            : recyclerView.getChildAt(0).getTop();
        swipeRefreshLayout.setEnabled(topRowVerticalPosition >= 0);
      }
    });
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
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

  public void addData(List<Object> data) {
    //TODO: add data to recycler adapter

    //easyRecyclerViewManager.addAll(data);
    adapter.addAll(data);
    //adapter.notifyItemRangeInserted(adapter.getItemCount(), data.size());

    emptyViewLayout.setVisibility(GONE);
    recyclerViewLayout.setVisibility(VISIBLE);
  }

  public void clearData() {
    //TODO: clear recycler adapter

    adapter.clear();
    adapter.notifyItemRangeRemoved(0, adapter.getItemCount());
    recyclerViewLayout.setVisibility(GONE);
    emptyViewLayout.setVisibility(VISIBLE);
  }
}
