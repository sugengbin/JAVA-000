### 学习笔记 FIXME

#### jvm参数关系图作业-参数含义
- Xmx：指定最大堆内存
- Xms：指定堆内存空间的初始大小
- Xmn：设置新生代初始和最大大小；设置过小会导致频繁GC，过大会导致GC一次时间过长；建议1/2~1/4
- Meta(-XX:MaxMetaspaceSize=size)：设置Meta空间大小
- DirectMemory(-XX:MaxDirectMemorySize=size)：设置系统可使用的最大堆外内存
- Xss：设置每个线程栈的字节数

