package code.hot.f;

import java.util.Stack;

public class a_29_42_trapping_rain_water {
    public  static int trap(int[] height) {
        int res = 0;
        //        从大到小
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                Integer top = stack.pop();
                if (!stack.isEmpty()) {
                    res += (Math.min(height[i], height[stack.peek()]) - height[top]) * (i - stack.peek() - 1);
                }
            }
            if (stack.isEmpty() || height[stack.peek()] != height[i]) {
                stack.push(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));//6
        System.out.println(trap(new int[]{5,2,1,2,1,5}));//14

    }
}
