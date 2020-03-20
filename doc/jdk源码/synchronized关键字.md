[点击这里](https://www.jianshu.com/p/e62fa839aa41)
注意：
其实wait/notify等方法也依赖于monitor对象，这就是为什么只有在同步的块或者方法中才能调用wait/notify等方法，否则会抛出java.lang.IllegalMonitorStateException的异常的原因。


monitor对象如何与对象关联起来的呢？
对象的几个部分的作用：

[点击这里](https://blog.csdn.net/scdn_cp/article/details/86491792)
[jvm内存和对象关系](https://www.jianshu.com/p/76959115d486)

1.对象头中的Mark Word（标记字）主要用来表示对象的线程锁状态，另外还可以用来配合GC、存放该对象的hashCode；
以上是Java对象处于5种不同状态时，Mark Word中64个位的表现形式，上面每一行代表对象处于某种状态时的样子。其中各部分的含义如下：

2.Klass Word是一个指向方法区中Class信息的指针，意味着该对象可随时知道自己是哪个Class的实例；

3.数组长度也是占用64位（8字节）的空间，这是可选的，只有当本对象是一个数组对象时才会有这个部分；

4.对象体是用于保存对象属性和值的主体部分，占用内存空间取决于对象的属性数量和类型；

5.对齐字是为了减少堆内存的碎片空间（不一定准确）。

对象有对象头，对象头mark word里面有一个锁的指针，如果被加了锁，这个指针指向monitor对象!