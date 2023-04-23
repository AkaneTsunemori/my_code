package code.hot.f;

import java.util.Stack;

public class a_37_232_MyQueue_from_stack {
    Stack<Integer> inStack ;
    Stack<Integer> outStack ;

    public a_37_232_MyQueue_from_stack() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    public void push(int x){
        inStack.push(x);
    }
    public int pop(){
        if(!outStack.isEmpty()){
            return outStack.pop();
        }else {
            while(!inStack.isEmpty()){
                outStack.push(inStack.pop());
            }
            if(!outStack.isEmpty()){
                return outStack.pop();
            }else {
                return -1;
            }
        }
    }
    public int peek(){
        if(!outStack.isEmpty()){
            return outStack.peek();
        }else {
            while(!inStack.isEmpty()){
                outStack.push(inStack.pop());
            }
            if(!outStack.isEmpty()){
                return outStack.peek();
            }else {
                return -1;
            }
        }
    }

    public boolean empty(){
        return inStack.isEmpty()&&outStack.isEmpty();
    }
}
