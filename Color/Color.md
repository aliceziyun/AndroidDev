## Color

*诸君，你也不想每次写color都硬编码吧*



#### Material Color

1. 在`res/values/colors.xml`中配置颜色

2. 在`res/values/themes.xml`引用颜色到主题中，名字和material文档中一样就可以覆盖

   https://github.com/material-components/material-components-android/blob/master/docs/theming/Color.md

3. 在`xml`文件或代码中引用该颜色

##### 问题：主题颜色被覆盖

在App启动的时候能看到一瞬间的Primary Color，然后瞬间被覆盖了

解决：

1. 一瞬间的颜色是`statusBarColor`导致的，需要将其设置为透明或白色
2. ToolBar的颜色需要`OnSurface`控制，在issue里有写
3. 不要忘记添加`android:fitsSystemWindows="true"`来与系统状态栏无缝集成



#### tint

动态调整图标、资源颜色，不用更改原始图标