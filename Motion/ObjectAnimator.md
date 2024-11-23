## `ObjectAnimator`

如果只涉及颜色、文字、大小、背景等属性的变化，推荐使用属性动画`ObjectAnimator`



#### 淡入淡出

##### 基本设置

```kotlin
val fadeOut = ObjectAnimator.ofFloat(button1, "alpha", 1f, 0f)
fadeOut.duration = 150

fadeOut.start()
fadeOut.cancel()
```

该代码表示，在`button1`上绑定该`fadeOut`动画，该动画的效果为将`button1`的透明度从1变为0

之后可以调用`start()`和`cancel()`函数启动动画效果

##### 监听器

```kotlin
fadeOut.addListener(object : AnimatorListenerAdapter() {
    override fun onAnimationEnd(animation: Animator) {
    	// made change on button1 here...
        
        fadeIn.start()
    }
})

override fun onAnimationStart(animation: Animator)
override fun onAnimationEnd(animation: Animator)
override fun onAnimationCancel(animation: Animator)
override fun onAnimationRepeat(animation: Animator)
```



参考：

https://blog.csdn.net/m0_37796683/article/details/90607428