package com.gigigo.multiplegridrecyclerview_demo;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.gigigo.multiplegridrecyclerview.MultipleGridRecyclerView;
import com.gigigo.multiplegridrecyclerview_demo.recyclerview.ImageWidget;
import com.gigigo.multiplegridrecyclerview_demo.recyclerview.ImageViewHolder;

public class MainActivity extends AppCompatActivity {

  private MultipleGridRecyclerView multipleGridRecyclerView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initViews();
  }

  private void initViews() {
    initFloatingActions();
    initMultipleGridRecyclerView();
  }

  private void initFloatingActions() {
    final FloatingActionsMenu floatingActionsMenu = (FloatingActionsMenu) findViewById(R.id.floating_action_menu);

    FloatingActionButton floatingActionButtonLoad =
        (FloatingActionButton) findViewById(R.id.floating_action_button_load);
    floatingActionButtonLoad.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        floatingActionsMenu.collapse();
        loadData();
      }
    });

    FloatingActionButton floatingActionButtonAdd =
        (FloatingActionButton) findViewById(R.id.floating_action_button_add);
    floatingActionButtonAdd.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        floatingActionsMenu.collapse();
        addData();
      }
    });

    FloatingActionButton floatingActionButtonClear =
        (FloatingActionButton) findViewById(R.id.floating_action_button_clear);
    floatingActionButtonClear.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        floatingActionsMenu.collapse();
        clearData();
      }
    });
  }

  private void initMultipleGridRecyclerView() {
    multipleGridRecyclerView = (MultipleGridRecyclerView) findViewById(R.id.multiple_grid_recycler_view);
    multipleGridRecyclerView.setAdapterDataViewHolder(ImageWidget.class, ImageViewHolder.class);
    multipleGridRecyclerView.setOnRefreshListener(new MultipleGridRecyclerView.OnRefreshListener() {
      @Override public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
          @Override public void run() {
            multipleGridRecyclerView.setRefreshing(false);
          }
        }, 3000);
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    Toast.makeText(this, "Layout mode: "+item.getTitle(),Toast.LENGTH_SHORT).show();
    switch (item.getItemId()) {
      case R.id.menu_1column_mode:
        multipleGridRecyclerView.setGridColumns(1);
        return true;
      case R.id.menu_2columns_mode:
        multipleGridRecyclerView.setGridColumns(2);
        return true;
      case R.id.menu_3columns_mode:
        multipleGridRecyclerView.setGridColumns(3);
        return true;
      case R.id.menu_4columns_mode:
        multipleGridRecyclerView.setGridColumns(4);
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private void loadData() {
    Display display = getWindowManager().getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    int width = size.x;
    int grid_columns = 3;
    multipleGridRecyclerView.loadData(DataGenerator.generateRandomImageDataList(30, width / grid_columns));
  }

  private void addData() {
    Display display = getWindowManager().getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    int width = size.x;
    int grid_columns = 3;
    multipleGridRecyclerView.addData(DataGenerator.generateRandomTextDataList(5, width / grid_columns));
  }

  private void clearData() {
    multipleGridRecyclerView.clearData();
  }
}
