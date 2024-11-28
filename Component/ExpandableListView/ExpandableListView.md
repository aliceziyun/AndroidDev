## ExpandableListView

带动画的版本参考：（均含Example）

https://github.com/tjerkw/Android-SlideExpandableListView

https://github.com/zaihuishou/ExpandableRecyclerview

### 基本用法

#### xml

1. 主界面中，直接添加该`ExpandableListView`

   ```xml
   <?xml version="1.0" encoding="utf-8"?>
   <ActionSlideExpandableListView
           android:id="@+id/list"
           android:layout_height="fill_parent"
           android:layout_width="fill_parent" />
   ```

2. 为每个item编写item layout

   panel和items在同一级别上，应该说panel就是item的一部分，不过是特殊的item

   ```xml
   <?xml version="1.0" encoding="utf-8"?>
   <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
       android:layout_width="fill_parent"
       android:layout_height="wrap_content"
       android:orientation="vertical">
       
       <RelativeLayout
           android:layout_width="fill_parent"
           android:layout_height="wrap_content"
           android:orientation="horizontal"
           android:id="@+id/item">
           ... view in panel
       </RelativeLayout>
       
       <LinearLayout
           android:layout_width="fill_parent"
           android:layout_height="wrap_content"
           android:orientation="horizontal"
           android:id="@+id/expandable"
           android:background="#000000">
           ... items
       </LinearLayout>
       
   </LinearLayout>
   ```

#### 使用

初始化List组件，创建Adapter，将数据与item视图与adapter绑定，再为ListView绑定Adapter即可



### 定制动画版

虽然从xml代码上看，panel元素和item元素依然在同一层级。但写法上比较有层次。

根据给出的例子，`Company`为panel元素，`Department`为item元素。

1. 首先装配`Company`，需要继承`ExpandableListItem`。该类维护了一个`Department`的List，重载方法`getChildItemList`返回department list。

   `Company`的视图在`CompanyItem`中设置。设置layout和具体初始化layout中的内部元素，参考重载方法`getLayoutResId`和`onBindViews`。（可以仿照`onExpansionToggled`和`onSetViews`里面的代码写一个展开时箭头变化的小动画）

2. 在activity中，需要重载adapter，使adapter知道点击的是什么item

   之后将数据与adapter绑定，将adapter与`RecyclerView`绑定即可