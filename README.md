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
- jshell

### JDK 10

---

- 局部变量的类型推断

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