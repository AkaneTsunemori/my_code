package slidingWindowMaxMin;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定数组arr, w为滑动窗口的大小, 返回滑动窗口滑动的过程中,窗口的最大值生成的数组
 */
public class Code01_SlidingWindowMaxArray {

    // 暴力的对数器方法
    public static int[] right(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        int N = arr.length;
        int[] res = new int[N - w + 1];
        int index = 0;
        int L = 0;
        int R = w - 1;
        while (R < N) {
            int max = arr[L];
            for (int i = L + 1; i <= R; i++) {
                max = Math.max(max, arr[i]);

            }
            res[index++] = max;
            L++;
            R++;
        }
        return res;
    }
    /**
     * 给定数组arr, w为滑动窗口的大小, 返回滑动窗口滑动的过程中,窗口的最大值生成的数组
     */
    public static int[] getMaxWindow(int[] arr, int w) {
        if(arr==null||arr.length<w||w<=0){
            return null;
        }
        int N = arr.length;
        int [] res = new int[N-w+1];
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0;i<w;++i){
            //关键的部分:
            while(!deque.isEmpty()&&arr[deque.peekLast()]<=arr[i]){
                deque.pollLast();
            }
            deque.addLast(i);
        }
        res[0] = arr[deque.peekFirst()];
        for (int L=1,R = w;R<N;++L,++R){
            while(!deque.isEmpty()&&arr[deque.peekLast()]<=arr[R]){
                deque.pollLast();
            }
            deque.addLast(R);
            while(deque.peekFirst()<L){
                deque.pollFirst();
            }
            res[L] = arr[deque.peekFirst()];
        }
        return res;
    }


    public static int[] slidingWindowMaxArray3(int[] arr, int size){
        if (arr == null || size < 1 || arr.length < size) {
            return null;
        }
        int N = arr.length;
        int[] res = new int[N-size+1];
        int index = 0;
        Deque<Integer>deque = new LinkedList<>();
        for(int R=0;R<N;++R){
            while (!deque.isEmpty()&&arr[deque.peekLast()]<=arr[R]){
                deque.pollLast();
            }
            deque.add(R);
            if(R>=size-1){
                if(deque.peekFirst()<R-size+1){
                    deque.pollFirst();
                }
                res[index++] = arr[deque.peekFirst()];
            }
        }
        return res;
    }

    public static int[] getMaxWindow2(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        // qmax 窗口最大值的更新结构
        // 放下标
        LinkedList<Integer> qmax = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int R = 0; R < arr.length; R++) {
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
                qmax.pollLast();
            }
            qmax.addLast(R);
            if (qmax.peekFirst() == R - w) {
                qmax.pollFirst();
            }
            if (R >= w - 1) {
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }


    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int w = (int) (Math.random() * (arr.length + 1));
            int[] ans1 = getMaxWindow(arr, w);
            int[] ans3 = slidingWindowMaxArray3(arr, w);
            if (!isEqual(ans1, ans3)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}
