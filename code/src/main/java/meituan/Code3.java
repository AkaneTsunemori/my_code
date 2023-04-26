package meituan;

import code.hot.ListNode;
import code.hot.TreeNode;

import java.util.*;

public class Code3 {
    class Solution41 {
        public String longestCommonPrefix(String[] strs) {
            String res = strs[0];
            for (int i = 1; i < strs.length; i++) {
                int j = 0;
                for (; j < res.length() && j < strs[i].length(); ++j) {
                    if (res.charAt(j) != strs[i].charAt(j)) break;
                }
                res = res.substring(0, j + 1);
            }
            return res;
        }
    }

    class Solution42 {
        public int removeDuplicates(int[] nums) {
            int cur = 0;
            for (int i = 1; i < nums.length; ) {
                if (nums[i] != nums[cur]) {
                    nums[++cur] = nums[i++];
                } else {
                    i++;
                }
            }
            return cur + 1;
        }
    }

    class Solution43 {
        public int trap(int[] height) {
            int res = 0;
//            上小,下大
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < height.length; i++) {
                while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                    Integer pop = stack.pop();
                    if (!stack.isEmpty())
                        res += (i - stack.peek() - 1) * (Math.min(height[i], height[stack.peek()]) - height[pop]);
                }
                stack.push(i);
            }
            return res;
        }

    }

    class Solution44 {
        public int[] getLeastNumbers(int[] arr, int k) {
            Queue<Integer> queue = new PriorityQueue<>();
            for (int i = 0; i < arr.length; i++) {
                while (queue.size() > k) {
                    queue.poll();
                }
                queue.add(arr[i]);
            }
            int[] res = new int[k];
            for (int i = 0; i < res.length; i++) {
                res[i] = queue.poll();
            }
            return res;
        }

    }

    class Solution45 {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) return res;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int size = queue.size();
            boolean reverse = false;
            while (!queue.isEmpty()) {
                Deque<Integer> inner = new ArrayDeque<>();
                while (size-- > 0) {
                    TreeNode poll = queue.poll();
                    if (poll.left != null) {
                        queue.add(poll.left);
                    }
                    if (poll.right != null) {
                        queue.add(poll.right);
                    }
                    if (reverse) {
                        inner.addFirst(poll.val);
                    } else {
                        inner.addLast(poll.val);
                    }

                }
                reverse = !reverse;
                size = queue.size();
                res.add(new ArrayList<>(inner));
            }
            return res;
        }

    }

    //需要转化为负数处理, 这里还要记得判断符号位应该通过无符号位移拿到最高位的值
    class Solution46 {
        public int reverse(int x) {
            boolean neg = (x >>> 31 & 1) == 1;
            x = neg ? x : -x;
            int m = Integer.MIN_VALUE / 10;
            int n = Integer.MIN_VALUE % 10;
            int res = 0;
            while (x != 0) {
                if (res < m || res == m && x % 10 < n) return 0;
                res = res * 10 + x % 10;
                x = x / 10;

            }
            return res;

        }

    }

    class Solution48 {
        public int majorityElement(int[] nums) {
            int curNum = nums[0];
            int count = 1;
            for (int i = 1; i < nums.length; i++) {
                if (count == 0) {
                    curNum = nums[i];
                    count++;
                } else if (nums[i] == curNum) {
                    count++;
                } else {
                    count--;
                }
            }
            return curNum;
        }

    }

    class Solution49 {
        public String reverseWords(String s) {
            int l = 0;

            int r = s.length() - 1;
            while (s.charAt(l) == ' ') {
                l++;
            }
            while (s.charAt(r) == ' ') {
                r--;
            }
            Deque<String> deque = new ArrayDeque<>();
            int cur = l;
            while (cur < r) {
                if (s.charAt(cur + 1) == ' ') {
                    deque.addFirst(s.substring(l, cur + 1));
                    cur++;
                    while (s.charAt(cur) == ' ') {
                        cur++;
                    }
                    l = cur;
                } else {
                    cur++;
                }
            }
            deque.addFirst(s.substring(l, cur + 1));
            return String.join(" ", deque);
        }

    }

    static class Solution50 {
        public static int climbStairs(int n) {
            if (n <= 2) return n;
            int[] dp = new int[n];
            dp[0] = 1;
            dp[1] = 2;
            for (int i = 2; i < n; ++i) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n - 1];
        }

        public static int climbStairs2(int n) {
            if (n <= 2) return n;
            int first = 1;
            int second = 2;
            int res = 0;
            int cur = 2;
            while (cur != n) {
                res = first + second;
                first = second;
                second = res;
                cur++;
            }
            return res;
        }
    }

    static class Solution51 {
        int res = 0;

        public int diameterOfBinaryTree(TreeNode root) {
            process(root);
            return res;
        }

        public int process(TreeNode root) {
            if (root == null) return 0;
            int left = process(root.left);
            int rigth = process(root.right);
            res = Math.max(res, left + rigth);
            return Math.max(left, rigth) + 1;
        }
    }

    //输入：nums = [10,9,2,5,3,7,101,18]
    //输出：4
    //解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
    static class Solution52 {
        public static int lengthOfLIS(int[] nums) {
            int[] dp = new int[nums.length];
            dp[0] = 1;
            int res = dp[0];
            for (int i = 1; i < nums.length; ++i) {
                dp[i] = 1;
                for (int j = i; j >= 0; --j) {
                    if (nums[j] < nums[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                res = Math.max(res, dp[i]);
            }
            return res;
        }
    }

    static class Solution53 {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == headB) return headA;
            ListNode nodeA = headA;
            ListNode nodeB = headB;
            while (nodeA != null && nodeB != null) {
                nodeA = nodeA.next;
                nodeB = nodeB.next;
            }
            if (nodeA == null) {
                nodeA = headB;
            } else {
                nodeB = headA;
            }
            while (nodeA != null && nodeB != null) {
                nodeA = nodeA.next;
                nodeB = nodeB.next;
            }
            if (nodeA == null) {
                nodeA = headB;
            } else {
                nodeB = headA;
            }
            while (nodeA != null && nodeB != null && nodeA != nodeB) {
                nodeA = nodeA.next;
                nodeB = nodeB.next;
            }
            return nodeA == nodeB ? nodeA : null;
        }

        public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
            ListNode nodeA = headA;
            ListNode nodeB = headB;
            //如果没有交点,两个都走完两个链表到达终点null,退出循环
            while (nodeA != nodeB) {
                if (nodeA == null) {
                    nodeA = headB;
                } else {
                    nodeA = nodeA.next;
                }
                if (nodeB == null) {
                    nodeB = headA;
                } else {
                    nodeB = nodeB.next;
                }
            }
            return nodeA;
        }
    }

    static class Solution54 {
        public static ListNode deleteDuplicates(ListNode head) {
            ListNode cur = head;
            while (cur != null && cur.next != null) {
                while (cur.next != null && cur.val == cur.next.val) {
                    cur.next = cur.next.next;
                }
                cur = cur.next;
            }
            return head;
        }

    }

    static class Solution55 {
        public boolean isSymmetric(TreeNode root) {
            if (root == null) return true;
            return process(root.left, root.right);
        }

        public boolean process(TreeNode left, TreeNode right) {
            if (left == null && right == null) {
                return true;
            }
            if (left == null) {
                return false;
            }
            if (right == null) {
                return false;
            }
            return left.val == right.val && process(left.left, right.right) && process(left.right, right.left);
        }

    }

    static class Solution56 {
        public int numIslands(char[][] grid) {
            int M = grid.length;
            int N = grid[0].length;
            int count = 0;
            for(int i = 0;i<M;++i){
                for (int j = 0;j<N;++j){
                    if(grid[i][j]==0){
                        continue;
                    }else {
                        count++;
                        process(grid ,i,j);
                    }
                }
            }
            return count;
        }

        private void process(char[][] grid, int i, int j) {
            if(i<0||i==grid.length||j<0||j==grid[0].length)return;
            grid[i][j]= 0;
            process(grid,i+1,j);
            process(grid,i-1,j);
            process(grid,i,j+1);
            process(grid,i,j-1);
        }
    }
    static class Solution57 {
        public int longestConsecutive(int[] nums) {
            int res = 1;
            Set<Integer>set = new HashSet<>();
            for (int num : nums) {
                set.add(num);
            }
            for (int num : nums) {
                if(set.contains(num-1)){
                    continue;
                }
                int count = 0;
                while(set.contains(num++)){
                    count++;
                }
                res = Math.max(count,res);
            }
            return res;
        }
    }
    //同爬楼梯
    static class Solution58 {

    }
    static class Solution59 {
        public static String minWindow(String s, String t) {
            Map<Character,Integer>ms = new HashMap<>();
            Map<Character,Integer>mt = new HashMap<>();
            for(int i = 0;i<t.length();++i){
                mt.put(t.charAt(i),mt.getOrDefault(t.charAt(i),0)+1);
            }
            int cnt =1;
            int count = Integer.MAX_VALUE;
            int resL = 0;
            int resR = 0;
            int l = 0,r = 0;
            for (; r < s.length();) {
                while(!check(ms,mt)&&r < s.length()){
                    ms.put(s.charAt(r),ms.getOrDefault(s.charAt(r),0)+1);
                    r++;
                    cnt++;
                }
                while(check(ms,mt)){
                    if(count>cnt){
                        count=cnt;
                        resL =l;
                        resR =r;

                    }
                    ms.put(s.charAt(l),ms.get(s.charAt(l))-1);
                    l++;
                    cnt--;
                }

            }
            return s.substring(resL,resR+1);
        }

        private static boolean check(Map<Character, Integer> ms, Map<Character, Integer> mt) {
            for (Map.Entry<Character, Integer> entry : mt.entrySet()) {
                if(ms.getOrDefault(entry.getKey(),0)<entry.getValue()){
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
//
        Solution59.minWindow("a","a");
    }


}
