package code.hot.g;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class a_50_239_max_sliding_window {
    public static  int[] maxSlidingWindow(int[]nums,int k){
        int[] res = new int[nums.length-k+1];
        Deque<Integer>deque = new ArrayDeque<>();
        for (int i = 0; i < k; i++) {
            while(!deque.isEmpty()&&nums[deque.peekLast()]<=nums[i]){
                deque.pollLast();
            }
            deque.addLast(i);
        }
        res[0] = nums[deque.peekFirst()];
        for(int l = 1,r = k;r<nums.length;++r,++l){
            while(!deque.isEmpty()&&nums[deque.peekLast()]<=nums[r]){
                deque.pollLast();
            }
            deque.addLast(r);
            while(deque.peekFirst()<l){
                deque.pollFirst();
            }
            res[l] = nums[deque.peekFirst()];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, -1}, 1)));
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, 3,-1,-3,5,3,6,7}, 3)));
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{7,2,4}, 2)));


    }
}
