## Suspend Function

https://kotlinlang.org/docs/coroutine-context-and-dispatchers.html

（指定Scope） --> builder --提供context- -> （指定dispatcher） --> 作用域中启动若干协程



#### Coroutine

比线程还要轻量级的运行单位

在user space创建和调度执行，不需要OS进行频繁的上下文切换，效率高



#### Context

协程需要在context中（可以理解成线程上下文）才能执行，context包含一个dispatcher

##### Dispatcher

决定该协程在哪个**线程**（或线程池）上执行

一些常见的dispatcher：

- no dispatcher：继承当前运行的context和其中的Dispatcher
- `Dispatcher.Main`：在主线程上执行
- `Dispatcher.Unconfined`：不受限制的dispatcher，协程一开始会继承caller的context，但当它再次挂起被唤醒后后，可能会到其他线程上执行。适用于既不需要CPU又不需要IO，没有特定需求的任务
- `Dispatcher.Default`：随便从公共线程池中挑一个执行
- `Dispachter.IO`：在IO线程池中执行，适合IO密集型任务，如写文件、读数据库等
- `newSingleThreadContext(/*new_thread_name*/)`：在自定义的线程上执行



#### Structured Concurrency

像是一个把四处乱跑的鸡圈住的鸡圈

存在invoke并发函数的作用域，当退出该作用域时，所有在其中并发的函数必须完成、失败或被取消。例如，如果该函数或者主程序出错了，没有完成的协程都会被自动取消。不会有主程序意外退出，并发程序还在后台跑的情况。

作用域可以嵌套，生效也是嵌套生效

suspend function表示该函数必须在协程的上下文中执行，需要有对应的scope来实现结构化并发

```kotlin
suspend fun concurrentSum(): Int = MainScope().launch {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    one.await() + two.await()
}
```

##### Scope

- `runBlocking`：主线程会等待该scope中的所有携程执行

- `MainScope`：在activity中创建的`MainScope`将自动把coroutine和activity的生命周期捆绑在一起。当activity被销毁时，释放所有协程，避免了内存泄漏

  `viewModelScope`也是类似的作用

