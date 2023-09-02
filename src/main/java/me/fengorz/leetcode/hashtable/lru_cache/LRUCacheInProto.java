package me.fengorz.leetcode.hashtable.lru_cache;
//运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
//
// 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。 
//写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在
//写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。 
//
// 
//
// 进阶: 
//
// 你是否可以在 O(1) 时间复杂度内完成这两种操作？ 
//
// 
//
// 示例: 
//
// LRUCacheInProto cache = new LRUCacheInProto( 2 /* 缓存容量 */ );
//
//cache.put(1, 1);
//cache.put(2, 2);
//cache.get(1);       // 返回  1
//cache.put(3, 3);    // 该操作会使得关键字 2 作废
//cache.get(2);       // 返回 -1 (未找到)
//cache.put(4, 4);    // 该操作会使得关键字 1 作废
//cache.get(1);       // 返回 -1 (未找到)
//cache.get(3);       // 返回  3
//cache.get(4);       // 返回  4
// 
// Related Topics 设计 
// 👍 951 👎 0


import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class LRUCacheInProto {

    private final Map<Integer, Integer> bucket;
    private final Deque<Integer> deque;
    private final int capacity;

    /**
     * Amazon Problems.
     * <p>
     * 使用原生API实现，性能较差
     *
     * @param capacity
     */
    public LRUCacheInProto(int capacity) {
        this.capacity = capacity;
        this.bucket = new HashMap<>(capacity);
        this.deque = new LinkedList<>();
    }

    public int get(int key) {
        Integer value = this.bucket.get(key);
        if (value == null) {
            return -1;
        } else {
            removeLru(key);
            return value;
        }
    }

    private void removeLru(int key) {
        this.deque.remove(key);
        this.deque.addLast(key);
    }

    public void put(int key, int value) {
        boolean notExist = this.bucket.get(key) == null;
        if (notExist && deque.size() == this.capacity) {
            this.bucket.remove(deque.pollFirst());
        }
        removeLru(key);
        this.bucket.put(key, value);
    }

}

/**
 * Your LRUCacheInProto object will be instantiated and called as such:
 * LRUCacheInProto obj = new LRUCacheInProto(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
