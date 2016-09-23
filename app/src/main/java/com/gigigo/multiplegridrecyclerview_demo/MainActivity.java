package com.gigigo.multiplegridrecyclerview_demo;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.gigigo.multiplegridrecyclerview.MultipleGridRecyclerView;
import com.gigigo.multiplegridrecyclerview.viewholder.MultipleGridViewHolder;
import com.gigigo.multiplegridrecyclerview_demo.recyclerview.CellImageViewHolder;
import com.gigigo.multiplegridrecyclerview_demo.recyclerview.CellImageWidget;
import com.gigigo.multiplegridrecyclerview_demo.recyclerview.ImageViewHolder;
import com.gigigo.multiplegridrecyclerview_demo.recyclerview.ImageWidget;
import com.gigigo.multiplegridrecyclerview_demo.recyclerview.TextViewHolder;
import com.gigigo.multiplegridrecyclerview_demo.recyclerview.TextWidget;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  TextView columnTextView;
  TextView ratioTextView;
  FloatingActionsMenu floatingDataActionsMenu;
  FloatingActionButton floatingActionButtonAddCellImage3x1;
  FloatingActionButton floatingActionButtonAddCellImage2x2;
  FloatingActionButton floatingActionButtonAddCellImage1x1;
  FloatingActionButton floatingActionButtonAddImage;
  FloatingActionButton floatingActionButtonAddText;
  FloatingActionButton floatingActionButtonClear;

  FloatingActionsMenu floatingAspectActionsMenu;
  FloatingActionButton floatingActionButtonMoreColumns;
  FloatingActionButton floatingActionButtonLessColumns;
  FloatingActionButton floatingActionButtonIncreaseRatio;
  FloatingActionButton floatingActionButtonDecreaseRatio;

  private MultipleGridRecyclerView multipleGridRecyclerView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initViews();
  }

  private void initViews() {
    initFloatingActions();
    initMultipleGridRecyclerView();
    initTextInfoView();
  }

  private void initFloatingActions() {
    initFloatingDataActions();
    initFloatingAspectActions();
  }

  private void initFloatingDataActions() {
    floatingDataActionsMenu = (FloatingActionsMenu) findViewById(R.id.floating_action_data_menu);
    floatingDataActionsMenu.setOnFloatingActionsMenuUpdateListener(
        new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
          @Override public void onMenuExpanded() {
            floatingAspectActionsMenu.collapse();
          }

          @Override public void onMenuCollapsed() {

          }
        });

    floatingActionButtonAddCellImage3x1 =
        (FloatingActionButton) findViewById(R.id.floating_action_button_add_cellimage_3x1);
    floatingActionButtonAddCellImage3x1.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        floatingDataActionsMenu.collapse();
        addCellImageData(3, 1);
      }
    });

    floatingActionButtonAddCellImage2x2 =
        (FloatingActionButton) findViewById(R.id.floating_action_button_add_cellimage_2x2);
    floatingActionButtonAddCellImage2x2.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        floatingDataActionsMenu.collapse();
        addCellImageData(2, 2);
      }
    });

    floatingActionButtonAddCellImage1x1 =
        (FloatingActionButton) findViewById(R.id.floating_action_button_add_cellimage_1x1);
    floatingActionButtonAddCellImage1x1.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        floatingDataActionsMenu.collapse();
        addCellImageData(1, 1);
      }
    });

    floatingActionButtonAddImage =
        (FloatingActionButton) findViewById(R.id.floating_action_button_add_image);
    floatingActionButtonAddImage.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        floatingDataActionsMenu.collapse();
        addImageData();
      }
    });

    floatingActionButtonAddText =
        (FloatingActionButton) findViewById(R.id.floating_action_button_add_text);
    floatingActionButtonAddText.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        floatingDataActionsMenu.collapse();
        addTextData();
      }
    });

    floatingActionButtonClear =
        (FloatingActionButton) findViewById(R.id.floating_action_button_clear);
    floatingActionButtonClear.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        floatingDataActionsMenu.collapse();
        clearData();
      }
    });
  }

  private void initFloatingAspectActions() {
    floatingAspectActionsMenu =
        (FloatingActionsMenu) findViewById(R.id.floating_action_aspect_menu);
    floatingAspectActionsMenu.setOnFloatingActionsMenuUpdateListener(
        new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
          @Override public void onMenuExpanded() {
            floatingDataActionsMenu.collapse();
          }

          @Override public void onMenuCollapsed() {

          }
        });

    floatingActionButtonMoreColumns =
        (FloatingActionButton) findViewById(R.id.floating_action_button_more_columns);
    floatingActionButtonMoreColumns.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        floatingAspectActionsMenu.collapse();
        moreColumns();
      }
    });

    floatingActionButtonLessColumns =
        (FloatingActionButton) findViewById(R.id.floating_action_button_less_columns);
    floatingActionButtonLessColumns.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        floatingAspectActionsMenu.collapse();
        lessColumns();
      }
    });

    floatingActionButtonIncreaseRatio =
        (FloatingActionButton) findViewById(R.id.floating_action_button_increase_ratio);
    floatingActionButtonIncreaseRatio.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        floatingAspectActionsMenu.collapse();
        increaseRatio();
      }
    });

    floatingActionButtonDecreaseRatio =
        (FloatingActionButton) findViewById(R.id.floating_action_button_decrease_ratio);
    floatingActionButtonDecreaseRatio.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        floatingAspectActionsMenu.collapse();
        decreaseRatio();
      }
    });
  }

  private void initMultipleGridRecyclerView() {
    multipleGridRecyclerView =
        (MultipleGridRecyclerView) findViewById(R.id.multiple_grid_recycler_view);

    setAdapterDataViewHolders();

    multipleGridRecyclerView.setOnRefreshListener(new MultipleGridRecyclerView.OnRefreshListener() {
      @Override public void onRefresh() {
        loadData();
        new Handler().postDelayed(new Runnable() {
          @Override public void run() {
            multipleGridRecyclerView.setRefreshing(false);
          }
        }, 3000);
      }
    });

    bindListeners();

    multipleGridRecyclerView.loadData(new ArrayList<>());
  }

  private void setAdapterDataViewHolders() {
    multipleGridRecyclerView.setAdapterDataViewHolder(CellImageWidget.class,
        CellImageViewHolder.class);
    multipleGridRecyclerView.setAdapterDataViewHolder(ImageWidget.class, ImageViewHolder.class);
    multipleGridRecyclerView.setAdapterDataViewHolder(TextWidget.class, TextViewHolder.class);
  }

  private void initTextInfoView() {
    columnTextView = (TextView) findViewById(R.id.columns_text_view);
    ratioTextView = (TextView) findViewById(R.id.ratio_text_view);

    updateColumnInfo(multipleGridRecyclerView.getGridColumns());
    updateRatioInfo(multipleGridRecyclerView.getCellAspectRatio());
  }

  private void updateRatioInfo(float cellAspectRatio) {
    ratioTextView.setText("Cell aspect ratio: " + cellAspectRatio);
  }

  private void updateColumnInfo(int columns) {
    columnTextView.setText("Grid columns: " + columns);
  }

  private void loadData() {
    Display display = getWindowManager().getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    int width = size.x;
    int grid_columns = 3;
    multipleGridRecyclerView.loadData(
        DataGenerator.generateRandomDataList(30, width / grid_columns));
  }

  private void addCellImageData(int column, int row) {
    multipleGridRecyclerView.add(DataGenerator.generateRandomCellImageData(column, row));
  }

  private void addImageData() {
    multipleGridRecyclerView.add(DataGenerator.generateRandomImageData());
  }

  private void addTextData() {
    multipleGridRecyclerView.add(DataGenerator.generateRandomTextData(99));
  }

  private void moreColumns() {
    int columns = multipleGridRecyclerView.getGridColumns();
    columns += 1;
    if (columns > 1) {
      floatingActionButtonLessColumns.setEnabled(true);
    }
    multipleGridRecyclerView.setGridColumns(columns);
    updateColumnInfo(columns);
  }

  private void lessColumns() {
    int columns = multipleGridRecyclerView.getGridColumns();
    if (columns <= 1) {
      floatingActionButtonLessColumns.setEnabled(false);
    } else {
      columns -= 1;
      multipleGridRecyclerView.setGridColumns(columns);
      updateColumnInfo(columns);
    }
  }

  private void increaseRatio() {
    float ratio = multipleGridRecyclerView.getCellAspectRatio();
    ratio += 0.1f;
    if (ratio > 0.1f) {
      floatingActionButtonDecreaseRatio.setEnabled(true);
    }
    multipleGridRecyclerView.setCellAspectRatio(ratio);
    updateRatioInfo(ratio);
  }

  private void decreaseRatio() {
    float ratio = multipleGridRecyclerView.getCellAspectRatio();
    if (ratio <= 0.1f) {
      floatingActionButtonDecreaseRatio.setEnabled(false);
    } else {
      ratio -= 0.1f;
      multipleGridRecyclerView.setCellAspectRatio(ratio);
      updateRatioInfo(ratio);
    }
  }

  private void clearData() {
    multipleGridRecyclerView.clearData();
  }

  private void bindListeners() {
    multipleGridRecyclerView.setItemClickListener(new MultipleGridViewHolder.OnItemClickListener() {
      @Override public void onItemClick(int position, View view) {
        Toast.makeText(view.getContext(), "Clicked position: " + position,
            Toast.LENGTH_SHORT).show();
      }
    });
    multipleGridRecyclerView.setItemLongClickListener(new MultipleGridViewHolder.OnItemLongClickListener() {
      @Override public boolean onItemLongClicked(int position, View view) {
        Toast.makeText(view.getContext(), "Long clicked position: " + position,
            Toast.LENGTH_SHORT).show();
        return false;
      }
    });
    multipleGridRecyclerView.setItemDragListener(new MultipleGridViewHolder.OnItemDragListener() {
      @Override public boolean OnItemDragged(int position, View view) {
        Toast.makeText(view.getContext(), "Dragged position: " + position,
            Toast.LENGTH_SHORT).show();
        return false;
      }
    });
  }
}
