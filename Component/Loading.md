## Loading

现在不怎么用`ProgressDialog`了。使用Material Progree Indicator

#### 全局loading

使用indicator的一个明显问题是需要往每个使用indicator的视图中都添加一个组件，并通过id获取，这样会有大量血压高的写id获取id环节。

不过我们可以使用全局的加载组件避免这个麻烦！

```kotlin
object LoadingIndicatorHelper {

    private var indicator: CircularProgressIndicator? = null
    
    fun showLoading(context: Context, rootView: ViewGroup) {
        if (indicator == null) {
            indicator = CircularProgressIndicator(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                isIndeterminate = true // 设置无限循环动画
            }
        }
        if (indicator?.parent == null) {
            rootView.addView(indicator)
        }
        indicator?.visibility = View.VISIBLE
    }

    fun hideLoading() {
        indicator?.visibility = View.GONE
    }
}
```

在调用这个组件的activity中：

```kotlin
LoadingIndicatorHelper.showLoading(context, activity.findViewById(R.id.content))
```

但这样做的话，转圈图标会出现在画面的左上角，并且UI不会变暗，看上去很蠢

建议使用`DialogFragment`进一步封装indicator