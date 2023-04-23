package code.hot.c;

import java.util.*;

public class a015_20_valid_parentheses {
    public boolean isValid(String s) {
        int length = s.length();
        if(length%2==1)return false;

        Map<Character,Character> map  =new HashMap<>();
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');
        Stack<Character> stack = new Stack<>();
        for(int i =0;i<s.length();++i){
            if(map.containsKey(s.charAt(i))){
                if(stack.isEmpty()||stack.peek()!=map.get(s.charAt(i))){
                    return false;
                }else{
                    stack.pop();
                }
            }else{
                stack.add(s.charAt(i));
            }

        }
        return stack.isEmpty();


    }
}
