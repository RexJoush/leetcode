package y2022.m02February.day483LRUCache;

import java.util.*;

/**
 * @author Rex Joush
 * @time 2022.02.02
 */
public class LRUCache2 extends LinkedHashMap<Integer, Integer> {

    // 缓存容量
    private int capacity;

    /*
        Java 有一种特殊的数据结构，LinkedHashMap
        即双端队列实现的 HashMap，非常契合本题目的效果。同时提供了一个方法，方法签名如下
            protected boolean removeEldestEntry(Map.Entry<K,V> eldest);
        即插入完成后，是否删除最老的 entry, 只需设置好开启此方法即可
        结果：
            43 ms, 86.27%
            108.1 MB, 61.51%
     */
    public LRUCache2(int capacity) {
        // 调用父类的构造方法，第二个参数为默认值
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    // 从哈希表中获取，有直接返回，否则返回 -1
    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        // 即，当大小大于容量的时候，移除最老的元素
        return size() > capacity;
    }
}

