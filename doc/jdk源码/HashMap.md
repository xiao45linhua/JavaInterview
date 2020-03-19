**更多 HashMap 与 ConcurrentHashMap 相关请查看[这里](https://crossoverjie.top/2018/07/23/java-senior/ConcurrentHashMap/)。**

# HashMap 底层分析

> 以下基于 JDK1.8 分析。

![](https://i.loli.net/2019/06/26/5d1391f87ca6355105.jpg)

如图所示，HashMap 底层是基于数组和链表实现的。其中有两个重要的参数：

- 容量: 数组的长度
- 负载因子：加载因子是表示Hsah表中元素的填满的程度.若:加载因子越大,填满的元素越多,好处是,空间利用率高了,但:冲突的机会加大了.反之,加载因子越小,填满的元素越少,好处是:冲突的机会减小了,但:空间浪费多了.
````java
    /**
     * Returns a power of two size for the given target capacity.
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
````

容量的默认大小是 16，默认的负载因子是 0.75，当 `HashMap` 的内置变量 `size > 16*0.75` 时就会发生扩容(容量和负载因子都可以自由调整)。
我们在使用的时候，无论我们传入的参数是什么，hashmap都会返回put之前存着的那个数，因此get和put方法其实差不多实现。

## put 方法
首先会将传入的 Key 做 `hash` 运算计算出 hashcode,然后根据数组长度取模计算出在数组中的 index 下标。

由于在计算中位运算比取模运算效率高的多，所以 HashMap 规定数组的长度为 `2^n` 。这样用 `2^n - 1` 做位运算与取模效果一致，并且效率还要高出许多。

由于数组的长度有限，所以难免会出现不同的 Key 通过运算得到的 index 相同，这种情况可以利用链表来解决，HashMap 会在 `table[index]`处形成链表(尾插法)，如果是链表长度大于8要转变为红黑树。

## get 方法

get 和 put 类似，也是将传入的 Key 计算出 index ，如果该位置上是一个链表就需要遍历整个链表，通过 `key.equals(k)` 来找到对应的元素。

## 遍历方式


````java
 Iterator<Map.Entry<String, Integer>> entryIterator = map.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, Integer> next = entryIterator.next();
            System.out.println("key=" + next.getKey() + " value=" + next.getValue());
        }
````

```java
Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            System.out.println("key=" + key + " value=" + map.get(key));

        }
```

```java
map.forEach((key,value)->{
    System.out.println("key=" + key + " value=" + value);
});
```

**强烈建议**使用第一种 EntrySet 进行遍历。

第一种可以把 key value 同时取出，第二种还得需要通过 key 取一次 value，效率较低, 第三种需要 `JDK1.8` 以上，通过外层遍历 table，内层遍历链表或红黑树。 

HashMap 则是综合考虑了所有因素，采用链地址法解决哈希冲突问题。这种方法是采用了 数组(哈希表)+ 链表的数据结构，当发生哈希冲突时，就用一个链表结构存储相同 Hash 值的数据。
那么哈希表是怎么 解决的呢?方式有很多，比如，开放定址法、再哈希函数法和链地址法。

## notice

在并发环境下1.7版本使用 `HashMap` 容易出现死循环。主要发生在transfer函数中，1.8在多线程环境下，会发生数据覆盖的情况

并发场景发生扩容，调用 `resize()` 方法里的 `rehash()` 时，容易出现环形链表。这样当获取一个不存在的 `key` 时，计算出的 `index` 正好是环形链表的下标时就会出现死循环。

![](https://i.loli.net/2019/06/26/5d1391f90375678382.jpg)

> 所以 HashMap 只能在单线程中使用，并且尽量的预设容量，尽可能的减少扩容。

在 `JDK1.8` 中对 `HashMap` 进行了优化：
当 `hash` 碰撞之后写入链表的长度超过了阈值(默认为8)并且 `table` 的长度不小于64(否则扩容一次)时，链表将会转换为**红黑树**。

假设 `hash` 冲突非常严重，一个数组后面接了很长的链表，此时重新的时间复杂度就是 `O(n)` 。

如果是红黑树，时间复杂度就是 `O(logn)` 。

大大提高了查询效率。

多线程场景下推荐使用 [ConcurrentHashMap](https://github.com/crossoverJie/Java-Interview/blob/master/MD/ConcurrentHashMap.md)。
