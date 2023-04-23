package code.hot.pdd;

import java.util.Stack;

public class MinStack {
    Stack<Integer> stack;
    Stack<Integer> minstack;

    public MinStack() {
        stack = new Stack<>();
        minstack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if (minstack.isEmpty()) {
            minstack.push(val);
        } else {
            Integer min = minstack.peek();
            if (val < min) {
                minstack.push(val);
            } else {
                minstack.push(min);
            }
        }
    }

    public void pop() {
        minstack.pop();
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minstack.peek();
    }
}
