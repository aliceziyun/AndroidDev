## Layout

除了熟悉基本的`LinearLayout`、`ConstraintLayout`、`FrameLayout`外，还应该掌握一些基本的场景，让图标动态适应布局



#### `FrameLayout`

在`FrameLayout`中，可以将多个视图添加到同一位置，但每次只显示一个视图。



#### `ConstraintLayout`





##### 实例1：设置元素间隙

在Layout中如果不设置间距，元素是紧挨着的。使用`marginEnd`等margin元素就可以添加间隙

##### 实例2：绕过系统状态栏

安卓应用中，组件容易被顶部或底部的导航栏遮住，在根布局中添加`android:fitsSystemWindows="true"`，就会自动调整布局以适应导航栏



