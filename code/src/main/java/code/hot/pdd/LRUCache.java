package code.hot.pdd;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private int capacity;
    private Map<Integer,Node> map;
    private Node head;
    private Node tail;

    public LRUCache(int capacity){
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }
    public int get(int key){
        if(map.containsKey(key)){
            Node node = map.get(key);
            removeNode(node);
            move2Head(node);
            return node.val;
        }
        return -1;
    }
    public void put (int key,int val){
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = val;
            removeNode(node);
            move2Head(node);
        }else {
            Node node = new Node(key, val);
            map.put(key,node);
            move2Head(node);
            if(map.size()>capacity){
                Node last = removeNode(tail.pre);
                map.remove(last.key);
            }
        }
    }

    private Node removeNode(Node node ) {
        Node pre = node.pre;
        Node next = node.next;
        pre.next = next;
        next.pre = pre;
        return node;
    }

    private void move2Head(Node node) {
        Node next = head.next;
        head.next = node;
        node.pre = head;
        node.next = next;
        next.pre = node;
    }

    class Node{
        Node pre;
        Node next;
        int key;
        int val;
        Node(){}
        Node(int key, int val){
            this.key = key;
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", val=" + val +
                    '}';
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2,1);
        System.out.println(lruCache.map);

        lruCache.put(1,1);
        System.out.println(lruCache.map);

        lruCache.put(2,3);

        System.out.println(lruCache.map);

        lruCache.put(4,1);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.map);

        System.out.println(lruCache.get(2));
        System.out.println(lruCache.map);

        System.out.println();

    }
}
