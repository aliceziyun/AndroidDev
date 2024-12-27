## Dart语言简介

https://book.flutterchina.club/



1. 强类型语言，变量与类型绑定，不能在运行时随意修改变量的类型

   一切变量皆类型（包括函数），存在类似Java Object这种可以通配所有类型的类型

2. final和const --> 和java用法完全一致

3. 和kotlin一样有null-safety机制，用?修饰的可空变量在使用前需检查其是否为空

4. 函数：

   - 函数作为变量：（该特性在之前的编程中没怎么用到，但在对于类型不确定的变量很有用）

     ```dart
     var say = (str){
       print(str);
     };
     say("hi world");
     ```

   - 函数作为参数

     ```dart
     //定义函数execute，它的参数类型为函数
     void execute(var callback) {
         callback(); //执行传入的函数
     }
     //调用execute，将箭头函数作为参数传递
     execute(() => print("xxx"))
     ```

5. mixin

   使用mixin关键字作为一种特殊的class，表示该类可以被作为一个“模块”与其他类结合

   用法：`class Example with A_do, B_do`

   而普通的class依然可以用`extends`继承

6. async

   - Future：进行耗时操作并在成功后返回数据

     ```dart
     Future.delayed(Duration(seconds: 2),(){
        return "hi world!";
     }).then((data){
        print(data);
     });
     ```

     如果需要等待多个Future任务执行，可以使用Future数组，如果数组的任何一个Future执行失败，就会走到失败分支

   - async/await（想起JavaScript了）

     1. 使用Future可以消除回调地狱，因为Future的返回值依然是Future

     2. 直接用await关键字获得普通编程体验

        ```dart
        task() async {
           try{
            String id = await login("alice","******");
            String userInfo = await getUserInfo(id);
            await saveUserInfo(userInfo);
            //执行接下来的操作   
           } catch(e){
            //错误处理   
            print(e);   
           }  
        }
        ```

        哦这可太像JavaScript了。（x）像JavaScript和Java的孩子……

     3. Stream，可以接受多次事件