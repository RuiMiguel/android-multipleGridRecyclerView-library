# MultipleGridRecyclerView
RecyclerView + GridLayout multiple cells types + empty view + swipe to refresh in a CustomView

Based on:
* [EasyRecyclerAdapter] to perform Adapters and ViewHolder factory
* [Plaid] for SpannedGridLayoutManager

### Version
2.0


[EasyRecyclerAdapter]: <https://github.com/CarlosMChica/easyrecycleradapters>
[Plaid]: <https://github.com/nickbutcher/plaid>

### How to use it

Use it with your own data model to show like a LinearLayout or GridLayout. Encapsulate your data model into Cell model to span by row/columns simultaneously. 
Even you can mix different data models.

####first init recyclerView

Create MultipleGridRecyclerView layout
```xml
  <com.ruialonso.multiplegridrecyclerview.MultipleGridRecyclerView
    android:id="@+id/multiple_grid_recycler_view"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
  />
```

####set adapters viewholders width its data model
```java
  multipleGridRecyclerView.setAdapterDataViewHolder(CellImageWidget.class, CellImageViewHolder.class);
  multipleGridRecyclerView.setAdapterDataViewHolder(ImageWidget.class, ImageViewHolder.class);
  multipleGridRecyclerView.setAdapterDataViewHolder(TextWidget.class, TextViewHolder.class);
```

####customize grid columns or cell aspect ratio dynamically
```java
  multipleGridRecyclerView.setGridColumns(columns);
  multipleGridRecyclerView.setCellAspectRatio(ratio);
```

####customize empty and loading views 
```xml
  <attr format="reference" name="empty_view_layout"/>
  <attr format="reference" name="loading_view_layout"/>
```
or 
```java
  multipleGridRecyclerView.setEmptyViewLayout(View)
  multipleGridRecyclerView.setEmptyViewLayoutByIdRes(@IdRes int)
  multipleGridRecyclerView.setEmptyViewLayoutByLayoutRes(@LayoutRes int)
  
  multipleGridRecyclerView.setLoadingViewLayout(View)
  multipleGridRecyclerView.setLoadingViewLayoutByIdRes(@IdRes int)
  multipleGridRecyclerView.setLoadingViewLayoutByLayoutRes(@LayoutRes int)
````

### Todos

 - 

License
----

Copyright 2016 Rui Miguel Alonso.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


