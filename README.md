# java-versions

记录`Java`的版本特性


### **JDK 1.5** 

---

- enum
- 泛型
- 自动装箱与拆箱
- 可变参数
- 增强循环

### JDK 1.6

---

- 支持脚本语言
- JDBC4.0API

### JDK 1.7 

---

- 支持try-with-resources
- switch语句块增加String支持
- NIO2.0包

### **JDK 1.8**

---

- [lambda表达式](java-8/src/main/java/com/diyishuai/java8/lambda/LambdaTest.java)

- [Stream API](java-8/src/main/java/com/diyishuai/java8/stream/StreamAPI.java)

- [新的日期时间的API](java-8/src/main/java/com/diyishuai/java8/LocalDateAndTimeAndDateTime.java)

- [默认/静态方法](java-8/src/main/java/com/diyishuai/java8/newinterface)

- [方法引用](java-8/src/main/java/com/diyishuai/java8/function/FunctionReference.java)

- [Optional](java-8/src/main/java/com/diyishuai/java8/optional/OptionalDemo.java)

- JVM的新特性

使用元空间`Metaspace`代替持久代（`PermGen space`），JVM参数使用`-XX:MetaSpaceSize`和`-XX:MaxMetaspaceSize`设置大小

### JDK 9 

---

- 模块化系统
- [不可变集合](java-9/src/main/java/com/di1shuai/java9/collection/UnmodifiableCollection.java)
- [接口私有方法](java-9/src/main/java/com/di1shuai/java9/interfece/PrivateInterface.java)
- 多版本兼容Jar包
- [try-with-resources的改进](java-9/src/main/java/com/di1shuai/java9/try_with_resources/TryWithResources.java)
- [Stream API 更新](java-9/src/main/java/com/di1shuai/java9/stream/StreamAPI.java)
- [Optional更新](java-9/src/main/java/com/di1shuai/java9/optional/OptionalDemo.java)
- JShell

### JDK 10

---

- [局部变量的类型推断](java-10/src/main/java/com/di1shuai/java10/variable/VariableDemo.java)
- [不可变集合 更新](java-10/src/main/java/com/di1shuai/java10/collection/UnmodifiableCollectionDemo.java)
- [Optional 更新 `orElseThrow()`](java-10/src/main/java/com/di1shuai/java10/optional/OptionalDemo.java)
- 并行全垃圾回收器G1
- 线程本地握手 
- 其他
    - 基于 Java 的 实验性 JIT 编译器
    - 类数据共享
    - Unicode 语言标签扩展
    - 根证书
    - 基于时间（Time-Based）的版本控制模型


### **JDK 11** 

---

- ZGC的引入
- Epsilon GC

### JDK 12

---

- switch表达式
- Shenandoah GC 
- 增强GC

### JDK 13

---

- switch表达式引入yield
- 文本块

### JDK 14

---

- instanceof模式识别
- Records
- 启用Parallel Scavenge+Serial GC组合
- 删除CMS GC