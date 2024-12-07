## `ViewModel`

https://developer.android.com/codelabs/basic-android-kotlin-training-viewmodel?hl=zh-cn#0

#### `viewModelFactory`



#### 与其他视图绑定

##### 实例1：拥有多个Fragment的Activity

如果Fragment被`remove`方法移除，那如果和`viewLifecycleOwner`绑定，Fragment被移除后则不会收到通知。

然而，如果使用`hide`方法隐藏Fragment，那么**Fragment是依然能收到ViewModel的消息的**！因为视图只是被缓存在了内存中，并没有消失。

需要根据数据读取和写入的需要来使用ViewModel绑定数据



#### [Q]神奇优化

```kotlin
fun getLyricsBySong(songId: Long) {
    viewModelScope.launch {
        delay(2000)
        _lyricsLine.value = lyricsRepository.getLyricsBySong(songId)
    }
}
```

ViewModel中绝对有隐藏机制，我只有第一次会触发该`delay(2000)`，后续虽然调用了这个函数但都是秒进入（我确信我是调用了这个函数的！）