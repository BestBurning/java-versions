### JDK 12

---

- [switch 增强](src/main/java/com/di1shuai/java12/switchdemo/SwitchDemo.java)
- [NumberFormat - 数字的格式化](src/main/java/com/di1shuai/java12/number/NumberFormatDemo.java)
- [Files.mismatch - 文件比较](src/main/java/com/di1shuai/java12/files/FilesDemo.java)
- [String API更新](src/main/java/com/di1shuai/java12/string/StringDemo.java)
- [Stream Teeing Collector](src/main/java/com/di1shuai/java12/stream/TeeingDemo.java)
- 其他
    - 支持unicode 11（684个新字符、11个新blocks、7个新脚本）
    - JVM 常量 API （主要在新的java.lang.invoke.constant包中定义了一系列基于值的符号引用类型，能够描述每种可加载常量。）
    - Shenandoah GC（低暂停时间垃圾收集器）
    - G1 收集器提升 （可中止的混合收集集合、及时返回未使用的已分配内存）
    - 默认CDS档案
    - JMH 基准测试