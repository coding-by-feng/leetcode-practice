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
// LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );
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


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class LRUCache {

    /**
     * 使用头尾节点，头节点空节点组成一个双向链表，空节点可以忽略临界问题，其真实的头节点代表即将被删除的最久未使用的数据
     */
    private Node head, tail;
    private Map<Integer, Node> bucket;
    private int capacity;
    private int size;

    /**
     * Amazon Problems.
     * <p>
     * 执行用时：19 ms, 在所有 Java 提交中击败了82.04%的用户
     * 内存消耗：46.1 MB, 在所有 Java 提交中击败了99.78%的用户
     *
     * @param capacity
     */
    public LRUCache(int capacity) {
        this.bucket = new HashMap<>(capacity);
        this.capacity = capacity;
        this.head = new Node();
        this.tail = new Node();
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public int get(int key) {
        Node node = this.bucket.get(key);
        if (node == null) {
            return -1;
        }
        useNode(node, node);
        return node.value;
    }

    /**
     * this.bucket溢出时删除头节点key值对应的数据即可
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        Node node = new Node(key, value);
        Node old = this.bucket.get(key);
        this.bucket.put(key, node);
        if (old == null) {
            this.size++;
            if (this.size > capacity) {
                trashFirst();
            }
        }
        useNode(old, node);
    }

    /**
     * 使用了节点
     */
    private void useNode(Node old, Node node) {
        if (old != null) {
            old.prev.next = old.next;
            old.next.prev = old.prev;
        }
        addLast(node);
    }

    /**
     * 丢弃头节点，由其后继节点接替
     */
    private void trashFirst() {
        Node first = this.head.next;
        this.head.next = first.next;
        first.next.prev = this.head;
        this.bucket.remove(first.key);
        this.size--;
    }

    /**
     * 在尾部插入
     *
     * @param node
     */
    private void addLast(Node node) {
        Node last = this.tail.prev;
        last.next = node;
        node.prev = last;
        node.next = this.tail;
        this.tail.prev = node;
    }

    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node() {
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
