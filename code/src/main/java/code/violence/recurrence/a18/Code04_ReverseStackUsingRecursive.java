package code.violence.recurrence.a18;

import java.util.Stack;

public class Code04_ReverseStackUsingRecursive {
    /**
     * 主函数
     * @param stack
     */
    public static void reverse(Stack<Integer> stack) {
        if(stack.isEmpty()){
            return;
        }else {
            int last = f(stack);
            reverse(stack);
            stack.push(last);
        }


    }
    /**
     * 该函数返回栈底元素,然后其他元素不变
     *eg: Stack:(顶)12345(底)  返回5 ,然后stack里还有1234
     *
     */
    public static int f(Stack<Integer>stack){
        Integer pop = stack.pop();
        if(stack.isEmpty()){
            return pop;
        }else{
            int last = f(stack);
            stack.push(pop);
            return last;
        }
    }
}
