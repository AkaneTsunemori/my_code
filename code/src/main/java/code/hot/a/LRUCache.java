package code.hot.a;

import code.hot.Node;

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    private Map<Integer, Node> map = new HashMap<>();
    private int capacity;
    private int size;
    private Node pre;
    private Node tail;
    public LRUCache(int capacity) {
        size=0;
        this.capacity = capacity;
        pre = new Node();
        tail = new Node();
        pre.next = tail;
        tail.pre= pre;

    }

    public int get(int key) {
        Node node = map.get(key);
        if(node==null)return -1;
        removeNode(node);
        addFirst(node);
        return node.val;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if(node==null){
            node = new Node(key,value);
            map.put(key,node);
            addFirst(node);
            size++;
            if(size>capacity){
                Node tobeRemoved = tail.pre;
                removeNode(tobeRemoved);
                map.remove(tobeRemoved.key);
                size--;
            }
        }else{
            node.val = value;
            removeNode(node);
            addFirst(node);
        }
    }
    public void addFirst(Node node){
        Node next = pre.next;
        node.next = next;
        node.pre = pre;
        pre.next = node;
        next.pre = node;


    }
    public void removeNode(Node node){
        Node pre = node.pre;
        Node next = node.next;
        pre.next = next;
        next.pre = pre;
    }
}
