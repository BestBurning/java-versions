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

### [JDK 1.7](java-7/) 

---

- 支持try-with-resources
- switch语句块增加String支持
- NIO2.0包

### [**JDK 1.8**](java-8/) - LTS

---

- [lambda表达式](java-8/src/main/java/com/diyishuai/java8/lambda/LambdaTest.java)
- [Stream API](java-8/src/main/java/com/diyishuai/java8/stream/StreamAPI.java)
- [新的日期时间的API](java-8/src/main/java/com/diyishuai/java8/LocalDateAndTimeAndDateTime.java)
- [默认/静态方法](java-8/src/main/java/com/diyishuai/java8/newinterface)
- [方法引用](java-8/src/main/java/com/diyishuai/java8/function/FunctionReference.java)
- [Optional](java-8/src/main/java/com/diyishuai/java8/optional/OptionalDemo.java)
- JVM的新特性

使用元空间`Metaspace`代替持久代（`PermGen space`），JVM参数使用`-XX:MetaSpaceSize`和`-XX:MaxMetaspaceSize`设置大小

### [JDK 9](java-9/) 

---

- 模块化系统
- [不可变集合](java-9/src/main/java/com/di1shuai/java9/collection/UnmodifiableCollection.java)
- [接口私有方法](java-9/src/main/java/com/di1shuai/java9/interfece/PrivateInterface.java)
- 多版本兼容Jar包
- [try-with-resources的改进](java-9/src/main/java/com/di1shuai/java9/try_with_resources/TryWithResources.java)
- [Stream API 更新](java-9/src/main/java/com/di1shuai/java9/stream/StreamAPI.java)
- [Optional更新](java-9/src/main/java/com/di1shuai/java9/optional/OptionalDemo.java)
- JShell

### [JDK 10](java-10/)

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


### [**JDK 11**](java-11/) - LTS 

---

- [String API更新](java-11/src/main/java/com/di1shuai/java11/string/StringDemo.java)
- [Lambda 参数的局部变量语法](java-11/src/main/java/com/di1shuai/java11/lambada/VariableDemo.java)
- ZGC
- Epsilon GC
- 单文件java命令直接编译运行
- [HTTP Client标准化](java-11/src/main/java/com/di1shuai/java11/http/HTTPClientDemo.java)

### [JDK 12](java-12/)

---

- [switch 增强](java-12/src/main/java/com/di1shuai/java12/switchdemo/SwitchDemo.java)
- [NumberFormat - 数字的格式化](java-12/src/main/java/com/di1shuai/java12/number/NumberFormatDemo.java)
- [Files.mismatch - 文件比较](java-12/src/main/java/com/di1shuai/java12/files/FilesDemo.java)
- [String API更新](java-12/src/main/java/com/di1shuai/java12/string/StringDemo.java)
- [Stream Teeing Collector](java-12/src/main/java/com/di1shuai/java12/stream/TeeingDemo.java)
- 其他
  - 支持unicode 11（684个新字符、11个新blocks、7个新脚本）
  - JVM 常量 API （主要在新的java.lang.invoke.constant包中定义了一系列基于值的符号引用类型，能够描述每种可加载常量。）
  - Shenandoah GC（低暂停时间垃圾收集器）
  - G1 收集器提升 （可中止的混合收集集合、及时返回未使用的已分配内存）
  - 默认CDS档案
  - JMH 基准测试

### [JDK 13](java-13)

---

- [switch表达式引入yield](java-13/src/main/java/com/di1shuai/java13/switchdemo/SwitchDemo.java)
- [文本块](java-13/src/main/java/com/di1shuai/java13/string/StringBlockDemo.java)
- ZGC 增强 - 释放未使用内存
- SocketAPI 重构

### [JDK 14](java-14)

---

- [instanceof模式识别 增强](java-14/src/main/java/com/di1shuai/java14/instance/InstanceOfDemo.java)
- [Record 类型](java-14/src/main/java/com/di1shuai/java14/record/RecordDemo.java)
- [异常信息提示改进](java-14/src/main/java/com/di1shuai/java14/nullexception/NullPointerExceptionDemo.java)
- 其他 
  - G1 的 NUMA 可识别内存分配
  - 删除 CMS GC
  - GC 支持 MacOS 和 Windows 系统