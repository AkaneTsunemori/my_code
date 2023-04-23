package code.hot.a;

import code.hot.Node;

import java.util.HashMap;
import java.util.Map;

/***
 * 手写一个LRU缓存
 *
 * 思路: 通过hashmap 和 双向链表实现
 *
 * 每次map访问到一个元素的时候, 将元素放到链表的最前端
 */
public class a003_146_LRUCache {

    private Node head;
    private Node tail;
    private Map<Integer, Node> map;
    private int capacity;
    private int curSize;


    public a003_146_LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
        curSize = 0;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node cur = map.get(key);
            removeNode(cur);
            addFirst(cur);
            return cur.val;

        }
        return -1;

    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node cur = map.get(key);
            cur.val = value;
            removeNode(cur);
            addFirst(cur);
        } else {
            Node cur = new Node(key, value);
            map.put(key, cur);
            addFirst(cur);
            curSize++;
            if (curSize > capacity) {
                map.remove(removeLast().key);
                curSize--;
            }
        }

    }

    public Node removeLast() {
        Node cur = tail.pre;
        Node pre = cur.pre;
        pre.next = tail;
        tail.pre = pre;
        return cur;
    }

    public void removeNode(Node cur) {
        Node pre = cur.pre;
        Node next = cur.next;
        pre.next = next;
        next.pre = pre;
    }

    public void addFirst(Node cur) {
        Node next = head.next;
        head.next = cur;
        cur.next = next;
        cur.pre = head;
        next.pre = cur;
    }

}

