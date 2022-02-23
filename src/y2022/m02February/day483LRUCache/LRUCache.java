package y2022.m02February.day483LRUCache;

import java.util.*;

/**
 * @author Rex Joush
 * @time 2022.02.02
 */

/*
    LRU 缓存
    https://leetcode-cn.com/problems/lru-cache/

    请你设计并实现一个满足 LRU (最近最少使用) 缓存 约束的数据结构。
    实现 LRUCache 类：
    LRUCache(int capacity) 以 正整数 作为容量capacity 初始化 LRU 缓存
    int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
    void put(int key, int value)如果关键字key 已经存在，则变更其数据值value ；如果不存在，则向缓存中插入该组key-value 。如果插入操作导致关键字数量超过capacity ，则应该 逐出 最久未使用的关键字。
    函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
    
    示例：
        输入
            ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
            [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
        输出
            [null, null, null, 1, null, -1, null, -1, 3, 4]
    
    解释
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        lRUCache.get(1);    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.get(2);    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lRUCache.get(1);    // 返回 -1 (未找到)
        lRUCache.get(3);    // 返回 3
        lRUCache.get(4);    // 返回 4
    
    提示：
        1 <= capacity <= 3000
        0 <= key <= 10000
        0 <= value <= 105
        最多调用 2 * 10^5 次 get 和 put

 */
public class LRUCache {

    // 缓存容量
    private int capacity;

    // 存储的缓存, 双端队列
    private Deque<Integer> deque;
    // 存储的缓存，哈希表
    private Map<Integer, Integer> cache;

    /*
        考虑使用双端队列，队尾元素永远保证为最久未使用, 保证 put 方法为 O(1)
        同时使用 HashMap 存储，保证 get 方法为 O(1)
        结果：
            1031 ms, 5.06%
            115.4 MB, 14.17%
     */
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.deque = new LinkedList<>();
        this.cache = new HashMap<>();
    }

    // 从哈希表中获取，有直接返回，否则返回 -1
    public int get(int key) {
        int value = cache.getOrDefault(key, -1);
        // 说明存在，则将此 key 移动到队列头部，表示最近使用了
        // 同时，移动到头部相当于，将当前节点删除，并将节点从头部再次添加。
        if (value != -1) {
            deque.remove(key);
            deque.addFirst(key);
        }
        return value;
    }

    public void put(int key, int value) {

        // 如果包含此键，更新值，并移动到队头
        if (cache.containsKey(key)) {
            // 更新值
            cache.put(key, value);
            deque.remove(key);
            deque.addFirst(key);
            return;
        }

        // 超过了容量，须先移除最久未使用的值
        if (capacity == 0) {
            // 最久的移除
            int last = deque.removeLast();
            cache.remove(last);
            capacity++;
        }
        // 将新的添加到队头，同时加入哈希表
        deque.addFirst(key);
        cache.put(key, value);
        // 容量减一
        capacity--;
    }
}

