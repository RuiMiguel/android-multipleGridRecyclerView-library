package com.gigigo.multiplegridrecyclerview_demo;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.gigigo.multiplegridrecyclerview.MultipleGridRecyclerView;

public class MainActivity extends AppCompatActivity {

  private MultipleGridRecyclerView recyclerView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initViews();
  }

  private void initViews() {
    FloatingActionButton floatingActionButtonAdd =
        (FloatingActionButton) findViewById(R.id.floating_action_button_add);
    floatingActionButtonAdd.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        addData();
      }
    });

    FloatingActionButton floatingActionButtonClear =
        (FloatingActionButton) findViewById(R.id.floating_action_button_clear);
    floatingActionButtonClear.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        clearData();
      }
    });

    recyclerView = (MultipleGridRecyclerView) findViewById(R.id.multiple_grid_recycler_view);
    recyclerView.setAdapterDataViewHolder(ImageData.class, ImageViewHolder.class);
  }

  private void addData() {
    Display display = getWindowManager().getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    int width = size.x;
    int grid_columns = 3;
    recyclerView.addData(DataGenerator.generateRandomDataList(width / grid_columns));
  }

  private void clearData() {
    recyclerView.clearData();
  }
}
