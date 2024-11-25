## PopupWindow

```kotlin
val popupView = layoutInflater.inflate(R.layout.custom_pop_up_menu_bar, null)

val popupWindow = PopupWindow(popupView,
    LinearLayout.LayoutParams.WRAP_CONTENT,
    LinearLayout.LayoutParams.WRAP_CONTENT)

popupWindow.isOutsideTouchable = true
popupWindow.isFocusable = true

popupWindow.showAsDropDown(v, /*x offset*/, /*y offset*/)
```

首先使用`layout`初始化View，再用View去初始化`PopupWindow`，最后将该window和某个组件绑定并显示（此处为显示在该组件下方）