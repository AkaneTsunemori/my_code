package slidingWindowMaxMin;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定数组arr 和value, gap表示一个窗口中最大值和最小值的差值,
 * 求这个数组中有多少个窗口的gap是小于等于value的
 */
public class Code02_AllLessNumSubArray {



    public static int allLessNumSubArray(int[] arr, int value) {
        int N = arr.length;
        Deque<Integer> dequeMax = new LinkedList<>();
        Deque<Integer> dequeMin = new LinkedList<>();
        int R = 0;
        int res = 0;
        for (int L = 0; L < N; ++L) {
            while (R < N) {
                while (!dequeMax.isEmpty() && arr[dequeMax.peekLast()] <= arr[R]) {
                    dequeMax.pollLast();
                }
                dequeMax.addLast(R);
                while (!dequeMin.isEmpty() && arr[dequeMin.peekLast()] >= arr[R]) {
                    dequeMin.pollLast();
                }
                dequeMin.addLast(R);
                if (arr[dequeMax.peekFirst()] - arr[dequeMin.peekFirst()]  > value) {
                    break;
                } else {
                    R++;
                }
            }
            res += (R - L);
            if (dequeMax.peekFirst() == L) {
                dequeMax.pollFirst();
            }
            if (dequeMin.peekFirst() == L) {
                dequeMin.pollFirst();
            }
        }
        return res;

    }

    // 暴力的对数器方法
    public static int right(int[] arr, int value) {
        if (arr == null || arr.length == 0 || value < 0) {
            return 0;
        }
        int N = arr.length;
        int count = 0;
        for (int L = 0; L < N; L++) {
            for (int R = L; R < N; R++) {
                int max = arr[L];
                int min = arr[L];
                for (int i = L + 1; i <= R; i++) {
                    max = Math.max(max, arr[i]);
                    min = Math.min(min, arr[i]);
                }
                if (max - min <= value) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int num(int[] arr, int value) {
        if (arr == null || arr.length == 0 || value < 0) {
            return 0;
        }
        int R = 0;
        int N = arr.length;
        int res = 0;
        Deque<Integer> dequeMax = new LinkedList<>();
        Deque<Integer> dequeMin = new LinkedList<>();
        for (int L = 0; L < N; ++L) {
            while (R < N) {
                while (!dequeMax.isEmpty() && arr[dequeMax.peekLast()] <= arr[R]) {
                    dequeMax.pollLast();
                }
                dequeMax.addLast(R);
                while (!dequeMin.isEmpty() && arr[dequeMin.peekLast()] >= arr[R]) {
                    dequeMin.pollLast();
                }
                dequeMin.addLast(R);
                if (arr[dequeMax.peekFirst()] - arr[dequeMin.peekFirst()] > value) {
                    break;
                } else {
                    R++;
                }
            }
            res += (R - L);
            if (!dequeMax.isEmpty() && dequeMax.peekFirst() == L) {
                dequeMax.pollFirst();
            }
            if (!dequeMin.isEmpty() && dequeMin.peekFirst() == L) {
                dequeMin.pollFirst();
            }
        }
        return res;

    }


    // for test
    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 200;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxLen, maxValue);
            int sum = (int) (Math.random() * (maxValue + 1));
            int ans1 = right(arr, sum);
            int ans2 = allLessNumSubArray(arr, sum);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(sum);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");

    }


}
