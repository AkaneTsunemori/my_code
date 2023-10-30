package a100;


import java.util.Stack;

/**
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 *
 *
 */
public class Problem_0014_LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        char[] chs = strs[0].toCharArray();
        int min = Integer.MAX_VALUE;
        for (String str : strs) {
            char[] tmp = str.toCharArray();
            int index = 0;
            while (index < tmp.length && index < min) {
                if (chs[index] != tmp[index]) {
                    break;
                }
                index++;
            }
            min = Math.min(index, min);
            if (min == 0) {
                return "";
            }
        }
        return strs[0].substring(0, min);
    }
    public static String longestCommonPrefix2(String[] strs) {
        String first = strs[0];
        int index = first.length();
        for (String str : strs) {
            for (int i = 0;i<Math.min(index,str.length());++i){
                if(str.charAt(i)!=first.charAt(i)){
                    index = i;
                    break;
                }
            }
        }
        return first.substring(0,index);
    }

    public static void main(String[] args) {
        System.out.println(Integer.valueOf("2"));
        System.out.println(calculate("3+2*2"));
    }

    static int i = 0;
    public static int calculate(String s) {
        Stack<String> stack = new Stack<>();
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                int cur = getDigit(s);
                if (!stack.isEmpty() && (stack.peek() == "*" || stack.peek() == "/")) {
                    String operator = stack.pop();
                    int num = Integer.valueOf(stack.pop());
                    int calculate = operator == "*" ? num * cur : num / cur;
                    stack.push(String.valueOf(calculate));
                } else {
                    stack.push(String.valueOf(cur));
                }
            } else {
                stack.push(String.valueOf(s.charAt(i++)));
            }
        }
        while (stack.size() != 1) {
            int num1 = Integer.valueOf(stack.pop());
            String operator = stack.pop();
            int num2 = Integer.valueOf(stack.pop());
            int calculate = operator == "+" ? num2 + num1 : num2 - num1;
            stack.push(String.valueOf(calculate));
        }
        return Integer.valueOf(stack.pop());


    }

    public static int getDigit(String s) {
        int rst = s.charAt(i++) - '0';
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            rst = rst * 10 + s.charAt(i++) - '0';
        }
        return rst;
    }
}
