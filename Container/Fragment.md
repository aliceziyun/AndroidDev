## `Fragment`

可以在程序运行时动态被添加到Activity中，便于模块化管理

#### 初始化

`Fragment`的布局在`onCreateView`函数中被初始化，使用`infalter`指定布局

在`onViewCreated`函数中，此时布局已被加载，可以对布局上的组件进行操作



#### 在`Activity`中的加载和切换

加载

```kotlin
val fragment = MyFragment()
supportFragmentManager.beginTransaction()
    .replace(R.id.fragment_container, fragment)
    .commit()
```

切换

```kotlin
val newFragment = AnotherFragment()
supportFragmentManager.beginTransaction()
    .replace(R.id.fragment_container, newFragment)
    .addToBackStack(null) // 添加到返回栈
    .commit()
```



#### 与`ViewModel`配合使用

`Activity`和`ViewModel`可以通过context共享同一`ViewModel`

```kotlin
viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
```

上面的代码在`Fragment`中被调用，`requireActivity`首先返回`Fragment`绑定的`Activity`，然后获取其中的`ViewModel`，实现了方便的数据和逻辑共享。



#### `FragmentManager`

如果应用需要反复在某几个`Fragment`中切换，一个直观的想法是用List维护这些`Fragment`，这样每次切换时就可以不用新创建。

不过`FragmentManager`已经自动集成了这个功能，在commit transaction时将`Fragment`的Tag添加到Manager中，之后就能用该Tag取出该`Fragment`

```kotlin
val fragmentManager = supportFragmentManager
    val tag = "MyFragment"

    // 查找已有的 Fragment
    var fragment = fragmentManager.findFragmentByTag(tag)
    if (fragment == null) {
        // 如果不存在，创建并添加
        fragment = MyFragment()
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment, tag)
            .commit()
    } else {
        // 如果存在，显示它（可以省略显示逻辑，若替换逻辑已经明确）
        fragmentManager.beginTransaction()
            .show(fragment)
            .commit()
    }
```



#### Hide

只初始化一次 Fragment，后续切换时通过 `hide()` 和 `show()`实现，避免视图重复加载。

##### 实例 在显示Fragment时加载数据

通过重载`onHiddenChanged()`方法，在Fragment显示状态变化时操作Fragment

```kotlin
override fun onHiddenChanged(hidden: Boolean) {
    super.onHiddenChanged(hidden)
    if (!hidden) {
        // Fragment 显示时加载数据
        loadDataAndUpdateUI()
    }
}
```



#### 监听Activity

