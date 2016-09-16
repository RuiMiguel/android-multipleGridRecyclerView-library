package com.gigigo.multiplegridrecyclerview_demo.recyclerview;

    public class Widget {
        private String text;
        private int colorRes;
        private GridType gridType;

        public Widget(String text, int colorRes, GridType gridType) {
            this.text = text;
            this.colorRes = colorRes;
            this.gridType = gridType;
        }

        public String getText() {
            return text;
        }

        public int getColorRes() {
            return colorRes;
        }

        public GridType getGridType() {
            return gridType;
        }

    }
