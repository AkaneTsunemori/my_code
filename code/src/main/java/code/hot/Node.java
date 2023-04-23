package code.hot;

public class Node {

    public int key;
    public int val;
    public Node pre;
    public Node next;

    public Node() {
    }

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
