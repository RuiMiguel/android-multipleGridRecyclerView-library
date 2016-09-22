package com.gigigo.multiplegridrecyclerview.entities;

public class Widget<T> {
  private T data;
  private int column;
  private int row;

  public Widget() {
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public int getColumn() {
    return column;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }
}
