package meituan;

import code.hot.ListNode;
import code.hot.TreeNode;

import java.util.*;

public class Code2 {
    class Solution21 {
        public String longestPalindrome(String s) {
            char[] charArray = getCharArray(s);
            int R = -1;
            int C = -1;
            int resC = -1;
            int max = -1;

            int[] pArr = new int[charArray.length];
            for (int i = 0; i < charArray.length; i++) {
                pArr[i] = R - i > 0 ? Math.min(pArr[2 * C - i], R - i) : 1;
                while (i + pArr[i] < charArray.length && i - pArr[i] >= 0 && charArray[i + pArr[i]] == charArray[i - pArr[i]]) {
                    pArr[i]++;
                }
                if (pArr[i] > max) {
                    max = pArr[i];
                    resC = i;
                }
                if (pArr[i] + i > R) {
                    R = pArr[i] + i;
                    C = i;
                }
            }
            return String.valueOf(charArray).substring(resC - max + 1, resC + max).replace("#", "");

        }

        public char[] getCharArray(String s) {
            char[] res = new char[s.length() * 2 + 1];
            for (int i = 0; i < res.length; i++) {
                res[i] = (i & 1) == 0 ? '#' : s.charAt(i / 2);
            }
            return res;
        }
    }

    //输入：s = "101023"
////输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
    class Solution22 {
        List<String> res = new ArrayList<>();

        public List<String> restoreIpAddresses(String s) {
            ipProcess(s, 0, new ArrayList<>());
            return res;
        }

        public void ipProcess(String s, int index, List<String> path) {

            if (index == s.length() && path.size() == 4) {
                res.add(String.join(".", path));
            } else {
                for (int i = index + 1; i <= s.length() && i - index <= 3; ++i) {

                    if (i - index >= 2 && s.charAt(index) == '0') {
                        continue;
                    }
                    if (i - index == 3 && Integer.parseInt(s.substring(index, i)) > 255) {
                        continue;
                    }

                    path.add(s.substring(index, i));

                    ipProcess(s, i, path);

                    path.remove(path.size() - 1);
                }
            }
        }
    }

    //给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
    class Solution23 {
        public int findLength(int[] nums1, int[] nums2) {
            int M = nums1.length;
            int N = nums2.length;
            int res = 0;
            int[][] dp = new int[M + 1][N + 1];
            for (int i = 1; i <= M; ++i) {
                for (int j = 1; j <= N; ++j) {
                    dp[i][j] = nums1[i - 1] == nums2[j - 1] ? dp[i - 1][j - 1] + 1 : 0;
                    res = Math.max(res, dp[i][j]);
                }
            }
            return res;
        }
    }

    class Solution24 {
        public List<Integer> spiralOrder(int[][] matrix) {
            int rowB = matrix.length - 1;
            int colR = matrix[0].length - 1;
            List<Integer> res = new ArrayList<>();

            int rowT = 0;
            int colL = 0;
            while (colL <= colR && rowT <= rowB) {
                for (int i = colL; i <= colR; i++) {
                    res.add(matrix[rowT][i]);
                }
                for (int i = rowT + 1; i <= rowB; i++) {
                    res.add(matrix[i][colR]);
                }
                if (rowT < rowB && colL < colR) {
                    for (int i = colR - 1; i >= colL; i--) {
                        res.add(matrix[rowB][i]);
                    }
                    for (int i = rowB - 1; i >= rowT + 1; i--) {
                        res.add(matrix[i][rowT]);
                    }
                }
                colL++;
                colR--;
                rowT++;
                rowB--;
            }
            return res;
        }
    }

    class Solution25 {
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> permute(int[] nums) {
            process(nums, 0);
            return res;
        }

        public void process(int[] nums, int index) {
            if (index == nums.length) {
                List<Integer> inner = new ArrayList<>();
                for (int num : nums) {
                    inner.add(num);
                }
                res.add(inner);
            } else {
                //[0,0,0,1,9]
                for (int i = index; i < nums.length; ++i) {
                    swap(nums, index, i);

                    process(nums, index + 1);

                    swap(nums, index, i);

                }
            }
        }

        public void swap(int[] nums, int m, int n) {
            int temp = nums[m];
            nums[m] = nums[n];
            nums[n] = temp;
        }
    }

    class Solution26 {
        public int myAtoi(String s) {
            return -1;
        }
    }

    class Solution27 {
        public ListNode getKthFromEnd(ListNode head, int k) {
            ListNode cur = head;
            while (k-- > 0) {
                cur = cur.next;
            }
            ListNode res = head;
            while (cur != null) {
                cur = cur.next;
                head = head.next;
            }
            return head;
        }
    }

    class Solution28 {
        List<Integer> res = new ArrayList<>();

        public List<Integer> preorderTraversal(TreeNode root) {
            process(root);
            return res;
        }

        public void process(TreeNode root) {
            if (root == null) return;
            res.add(root.val);
            process(root.left);
            process(root.right);
        }
    }

    class Solution29 {
        public ListNode mergeKLists(ListNode[] lists) {
            Queue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));
            for (ListNode node : lists) {
                if (node != null) queue.add(node);
            }
            ListNode pre = new ListNode();
            ListNode hair = pre;
            while (!queue.isEmpty()) {
                ListNode poll = queue.poll();
                pre.next = poll;
                pre = poll;
                if (poll.next != null) {
                    queue.add(poll.next);
                }
            }
            return hair.next;
        }
    }

    class Solution30 {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode cur = head;
            ListNode pre = new ListNode();
            ListNode res = pre;
            pre.next = head;
            while (n-- > 0) {
                cur = cur.next;
            }
            while (cur != null) {
                cur = cur.next;
                pre = pre.next;
            }
            pre.next = pre.next.next;
            return res.next;
        }
    }

    class Solution31 {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == p || root == q || root == null) {
                return root;
            }

            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (left == null) return right;
            if (right == null) return left;
            return root;
        }
    }

    class Solution32 {
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode pre = new ListNode();

            ListNode hair = pre;
            pre.next = head;
            int count = k - 1;
            ListNode cur = head;

            ListNode start = head;
            ListNode end;
            ListNode nextStart;

            while (cur != null && cur.next != null) {
                while (count-- > 0 && cur.next != null) {
                    cur = cur.next;
                }
                if (count > -1) {
                    return hair.next;
                }

                end = cur;
                nextStart = end.next;

                end.next = null;

                ListNode[] reverse = reverse(start);
                pre.next = reverse[0];
                pre = reverse[1];
                pre.next = nextStart;

                start = nextStart;
                cur = nextStart;
                count = k - 1;

            }
            return hair.next;
        }

        public ListNode[] reverse(ListNode start) {
            ListNode pre = null;
            ListNode end = start;
            while (start != null) {
                ListNode next = start.next;
                start.next = pre;
                pre = start;
                start = next;
            }
            return new ListNode[]{pre, end};
        }
    }

    class Solution33 {
        public String addStrings(String num1, String num2) {
            int index1 = num1.length() - 1;
            int index2 = num2.length() - 1;
            int add = 0;
            StringBuilder sb = new StringBuilder();
            while (index1 >= 0 && index2 >= 0) {
                int sum = add + num1.charAt(index1--) - '0' + num2.charAt(index2--) - '0';
                int mod = sum % 10;
                add = sum / 10;
                sb.append(mod);
            }
            while (index1 >= 0) {
                int sum = add + num1.charAt(index1--) - '0';
                int mod = sum % 10;
                add = sum / 10;
                sb.append(mod);
            }
            while (index2 >= 0) {
                int sum = add + num2.charAt(index2--) - '0';
                int mod = sum % 10;
                add = sum / 10;
                sb.append(mod);
            }
            if (add > 0) sb.append(add);
            return sb.reverse().toString();
        }
    }

    class Solution34 {
        public int longestCommonSubsequence(String text1, String text2) {
            int M = text1.length();
            int N = text2.length();
            int[][] dp = new int[M + 1][N + 1];
            for (int i = 1; i <= M; ++i) {
                for (int j = 1; j <= N; ++j) {
                    dp[i][j] = text1.charAt(i - 1) == text2.charAt(j - 1) ? dp[i - 1][j - 1] + 1 : Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
            return dp[M][N];
        }
    }

    class Solution35 {
        public int search(int[] nums, int target) {
            int l = 0;
            int r = nums.length - 1;
            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                if (nums[l] <= nums[mid]) {
                    int res = subSearch(nums, target, l, mid);
                    if (res != -1) {
                        return res;
                    }
                    l = mid + 1;
                } else {
                    int res = subSearch(nums, target, mid, r);
                    if (res != -1) {
                        return res;
                    }
                    r = mid - 1;
                }
            }
            return -1;
        }

        public int subSearch(int[] nums, int target, int l, int r) {
            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                if (nums[mid] == target) return mid;
                if (nums[mid] < target) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            return -1;
        }

    }

     class Solution36 {
        public  int mySqrt(int x) {
            int l = 1;
            int r = x;
            while(l<=r){
                int mid= l + ((r - l) >> 1);

                long mix = (long)mid * mid;
                if(mix == x){
                    return mid ;
                }else if(mix>x){
                    r =mid-1;
                }else {
                    l = mid+1;
                }
            }
            return r;
        }

    }

     class Solution37 {
        public  int[] maxSlidingWindow(int[] nums, int k) {
            int[] res = new int[nums.length-k+1];
            Deque<Integer>deque = new ArrayDeque<>();
            for(int i = 0;i<k;++i){
                while(!deque.isEmpty()&&nums[deque.peekLast()]<=nums[i]){
                    deque.pollLast();
                }
                deque.addLast(i);
            }
            int count = 0;
            res[count++] = nums[deque.peekFirst()];
            for(int i = k;i<nums.length;++i){
                while(!deque.isEmpty()&&nums[deque.peekLast()]<=nums[i]){
                    deque.pollLast();
                }
                deque.addLast(i);
                while(deque.peekFirst()<=i-k){
                    deque.pollFirst();
                }
                res[count++] = nums[deque.peekFirst()];
            }
            return res;
        }
    }
     class Solution38 {
         List<Integer> res = new ArrayList<>();
         public List<Integer> inorderTraversal(TreeNode root) {
             process(root);
             return res;
         }
         public void process(TreeNode root){
             if(root==null)return ;
             process(root.left);
             res.add(root.val);
             process(root.right);
         }

    }
    class Solution39 {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode pre = new ListNode();
            ListNode res = pre;
            int add = 0;
            while(l1!=null&&l2!=null){
                int sum = l1.val+l2.val+add;
                add = sum/10;
                int mod = sum%10;
                pre.next = new ListNode(mod);
                l1 = l1.next;
                l2 = l2.next;
                pre = pre.next;

            }
            while(l1!=null){
                int sum = l1.val+add;
                add = sum/10;
                int mod = sum%10;
                pre.next = new ListNode(mod);
                l1 = l1.next;
                pre = pre.next;
            }
            while(l2!=null){
                int sum = l2.val+add;
                add = sum/10;
                int mod = sum%10;
                pre.next = new ListNode(mod);
                l2 = l2.next;
                pre = pre.next;
            }
            if(add!=0){
                pre.next = new ListNode(add);
            }
            return res.next;
        }

    }
    class Solution40 {
        public int maxProfit(int[] prices) {
            int res = 0;
            int min = prices[0];
            for (int i = 1; i < prices.length; i++) {
                if(prices[i]<min){
                    min = prices[i];
                }else {
                    res = Math.max(res,prices[i]-min);
                }
            }
            return res;
        }

    }

    public static void main(String[] args) {
    }


}

