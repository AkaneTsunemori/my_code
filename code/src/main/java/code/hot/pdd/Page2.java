package code.hot.pdd;

import code.hot.ListNode;

import java.util.*;

public class Page2 {
    /**
     * map里存val 和list中的index位置, 在移除的时候直接讲list的最后一个和需要移除的位置对调,然后移除最后一个就可以保证o(1)的复杂度了
     */
    class RandomizedSet {
        List<Integer> list;
        Map<Integer, Integer> map;

        public RandomizedSet() {
            list = new ArrayList<>();
            map = new HashMap<>();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            } else {
                list.add(val);
                map.put(val, list.size() - 1);
                return true;
            }
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            } else {
                Integer index = map.get(val);
                list.set(index, list.get(list.size() - 1));
                list.remove(list.size() - 1);
                map.remove(val);
                return true;
            }

        }

        public int getRandom() {
            return list.get((int) (Math.random() * list.size()));
        }

    }


    public ListNode sortList148(ListNode head) {
        ListNode pre = head;
        while (head.next != null) {
            head = head.next;
        }
        ListNode tail = head;
        return sort(pre, tail);
    }

    public ListNode sort(ListNode head, ListNode tail) {
        if (head == tail) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow;
        ListNode midNext = mid.next;
        mid.next = null;
        ListNode node1 = sort(head, mid);
        ListNode node2 = sort(midNext, tail);
        return merge(node1, node2);
    }

    public ListNode merge(ListNode node1, ListNode node2) {
        if (node1 == null) return node2;
        if (node2 == null) return node1;
        if (node1.val > node2.val) {
            node2.next = merge(node1, node2.next);
            return node2;
        } else {
            node1.next = merge(node1.next, node2);
            return node1;
        }
    }

    public int[][] generateMatrix59(int n) {
        int[][] res = new int[n][n];
        int rowTop = 0, rowButton = n - 1;
        int colLeft = 0, colRight = n - 1;
        int num = 1;
        while (rowTop <= rowButton) {
            for (int i = colLeft; i <= colRight; ++i) {
                res[rowTop][i] = num++;
            }
            for (int i = rowTop + 1; i <= rowButton; ++i) {
                res[i][colRight] = num++;
            }
            for (int i = colRight - 1; i >= colLeft; --i) {
                res[rowButton][i] = num++;
            }
            for (int i = rowButton - 1; i >= rowTop + 1; --i) {
                res[i][colLeft] = num++;
            }
            rowTop++;
            rowButton--;
            colLeft++;
            colRight--;
        }
        return res;
    }

    //输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2
//输出：6
//解释：[1,1,1,0,0,1,1,1,1,1,1]
    public int longestOnes1004(int[] nums, int k) {
        int sumR = 0;
        int sumL = 0;
        int L = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            sumR += nums[i] == 0 ? 1 : 0;
            while (sumR - sumL > k) {
                sumL += nums[L] == 0 ? 1 : 0;
                L++;
            }
            res = Math.max(res, i - L + 1);
        }
        return res;
    }

    public double cubeFun(double x) {

        return 0;
    }


    public double myPow(double x, int n) {
        if (n == 0) return 1;
        boolean neg = n < 0;
        return neg ? 1 / quickMul(x, -n) : quickMul(x, -n);
    }

    public double quickMul(double x, long N) {
        if (N == 1) {
            return x;
        }
        double v = quickMul(x, N / 2);
        return (N & 1) == 0 ? v * v : v * v * x;
    }

    public int majorityElement169(int[] nums) {
        int cur = 0;
        int count = 0;
        for (int num : nums) {
            if (count == 0) {
                cur = num;
                count++;
            } else if (cur == num) {
                count++;
            } else {
                count--;
            }
        }
        return cur;
    }

    int num = 0;

    public int maxAreaOfIsland695(int[][] grid) {
        int res = 0;
        int rowNum = grid.length;
        int colNum = grid[0].length;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; ++j) {
                num = 0;
                count(grid, i, j);
                res = Math.max(res, num);
            }
        }
        return res;
    }

    public void count(int[][] grid, int i, int j) {
        if (i < 0 || i == grid.length || j < 0 || j == grid[0].length || grid[i][j] == 0) {
            return;
        }
        grid[i][j] = 0;
        num++;
        count(grid, i + 1, j);
        count(grid, i - 1, j);
        count(grid, i, j + 1);
        count(grid, i, j - 1);
    }

    List<String> parenthesis = new ArrayList<>();

    public List<String> generateParenthesis22(int n) {
        generate(0, 0, n, "");
        return parenthesis;
    }

    public void generate(int left, int right, int n, String path) {
        if (left == n && left == right) {
            parenthesis.add(path);
        } else if (left == n) {
            generate(left, right + 1, n, path + ")");
        } else if (left == right) {
            generate(left + 1, right, n, path + "(");
        } else {
            generate(left + 1, right, n, path + "(");
            generate(left, right + 1, n, path + ")");
        }
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int sum;
        ListNode pre = new ListNode();
        ListNode cur = pre;
        int ans = 0;
        while (l1 != null && l2 != null) {
            sum = l1.val + l2.val + ans;
            ans = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            sum = l1.val + ans;
            ans = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l2 != null) {
            sum = l2.val + ans;
            ans = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        if (ans != 0) {
            cur.next = new ListNode(ans);
        }
        return pre.next;
    }

    int coinCount = Integer.MAX_VALUE;

    public int coinChange322_01(int[] coins, int amount) {
        coinChangeProcess(coins, amount, 0);
        return coinCount == Integer.MAX_VALUE ? -1 : coinCount;
    }

    public void coinChangeProcess(int[] coins, int rest, int count) {
        if (rest == 0) {
            coinCount = Math.min(coinCount, count);
        }
        for (int coin : coins) {
            int i = 1;
            while (coin * i <= rest) {
                coinChangeProcess(coins, rest - coin * i, count + i);
                i++;
            }
        }
    }

    public int coinChange322_02(int[] coins, int amount) {

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int coin : coins) {
            if (coin < dp.length)
                dp[coin] = 1;
        }
        dp[0] = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    List<List<Integer>> permuteRes = new ArrayList<>();

    public List<List<Integer>> permute46(int[] nums) {
        permuteProcess(nums, 0);
        return permuteRes;
    }

    public void permuteProcess(int[] nums, int index) {
        if (index == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int i : nums) {
                list.add(i);
            }
            permuteRes.add(list);
        } else {
            for (int i = index; i < nums.length; i++) {
                swap(nums, index, i);
                permuteProcess(nums, index + 1);
                swap(nums, index, i);
            }
        }
    }

    public void swap(int[] arr, int m, int n) {
        int temp = arr[n];
        arr[n] = arr[m];
        arr[m] = temp;
    }

    public boolean canAttendMeetings252(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> {
            return o1[0] - o2[0];
        });
        for (int i = 0; i < intervals.length; i++) {
            if (i < intervals.length - 1 && intervals[i][1] > intervals[i + 1][0]) {
                return false;
            }
        }
        return true;
    }


    public int rob198(int[] nums) {
        int M = nums.length;
        int[] dp = new int[M];
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < dp.length; i++) {
            int max = 0;
            for (int j = 0; j < i-1; j++) {
                max = Math.max(max,dp[j]);
            }
            dp[i] = max+nums[i];
            res = Math.max(res,dp[i]);

        }
        return res;
    }


    public static void main(String[] args) {
        Page2 page2 = new Page2();
        System.out.println(page2.rob198(new int[]{1}));
    }

}
