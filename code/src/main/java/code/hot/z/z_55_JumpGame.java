package code.hot.z;

import java.util.PriorityQueue;
import java.util.Queue;

public class z_55_JumpGame {
    public int[] inventoryManagement(int[] stock, int cnt) {
        Queue<Integer> queue = new PriorityQueue<>(cnt);
        for (int i = 0; i < stock.length; i++) {
            queue.offer(stock[i]);
        }
        int[]rst = new int[cnt];
        for (int i = 0; i < cnt; i++) {
            rst[i] = queue.poll();
        }
        return rst;
    }
}
