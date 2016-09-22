package com.gigigo.multiplegridrecyclerview_demo.recyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.gigigo.multiplegridrecyclerview.viewholder.MultipleGridViewHolder;
import com.gigigo.multiplegridrecyclerview_demo.R;

public class TextViewHolder extends MultipleGridViewHolder<TextWidget> {

  private TextView textView;

  public TextViewHolder(Context context, ViewGroup parent) {
    super(context, parent, R.layout.content_item_text_element);
    textView = (TextView) this.itemView.findViewById(R.id.text_view);

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

  @Override public void bindTo(TextWidget item) {
    textView.setText(item.getText());
  }
}
