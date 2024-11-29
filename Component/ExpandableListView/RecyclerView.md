## `RecyclerView`

`ListView`的全面上位替代

注：在使用`RecyclerView`显示list时，需要在代码中设置`LayoutManager`，否则内容显示不出来

```kotlin
recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
```

原因：`RecyclerView`不负责列表数据的管理，不知道列表数据具体的排布方式。而一些高级排布方式（例如网格、瀑布流等）需要动态设置，不支持在xml中直接设置*（有点奇怪）*。



#### `ViewHolder`

将每个列表项已创建的视图缓存在`ViewHolder`中，当需要显示视图时，直接复用`ViewHolder`中的视图