package meituan;

import code.hot.ListNode;
import code.hot.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code4 {
    static class Solution61 {
        public static double myPow(double x, int n) {
            long m = n;
            if (m < 0) {
                return 1 / pow(x, -m);
            } else {
                return pow(x, m);
            }
        }

        public static double pow(double x, long n) {
            if (n == 0) return 1;
            if ((n & 1) == 0) {
                double v = pow(x, n >> 1);
                return v * v;
            } else {
                double v = pow(x, n >> 1);
                return v * v * x;
            }
        }
    }

    static class Solution62 {
        public int maxDepth(TreeNode root) {
            if (root == null) return 0;
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            return Math.max(left, right) + 1;
        }
    }

    static class Solution63 {
        public int minPathSum(int[][] grid) {
            int M = grid.length;
            int N = grid[0].length;
            int[][] dp = new int[M][N];
            dp[0][0] = grid[0][0];
            for (int i = 1; i < M; i++) {
                dp[i][0] = grid[i][0] + dp[i - 1][0];
            }
            for (int i = 1; i < N; i++) {
                dp[0][i] = grid[0][i] + dp[0][i - 1];
            }
            for (int i = 1; i < M; i++) {
                for (int j = 1; j < N; ++j) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
            return dp[M - 1][N - 1];
        }
    }

    static class Solution64 {
        public static String validIPAddress(String queryIP) {
            int l = 0;
            int r = 0;
            int count = 0;
            if (queryIP.contains(".")) {
                while (r < queryIP.length()) {
                    while (r < queryIP.length() && queryIP.charAt(r) != '.') {
                        char c = queryIP.charAt(r);
                        if (r - l > 3) return "Neither";
                        if (c < '0' || c > '9') return "Neither";
                        r++;
                    }
                    if (queryIP.charAt(l) == '0' && r > l + 1) return "Neither";
                    if (l == r || Integer.parseInt(queryIP.substring(l, r)) > 255) return "Neither";
                    count++;
                    if (count == 4 && r != queryIP.length()) return "Neither";
                    l = r + 1;
                    r = l;
                }
                return count == 4 ? "IPv4" : "Neither";

            } else {
                while (r < queryIP.length()) {
                    while (r < queryIP.length() && queryIP.charAt(r) != ':') {
                        char c = queryIP.charAt(r);
                        if (r - l >= 4) return "Neither";
                        if (!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F')))
                            return "Neither";
                        r++;
                    }
                    if (l == r) return "Neither";
                    count++;
                    if (count == 8 && r != queryIP.length()) return "Neither";
                    l = r + 1;
                    r = l;
                }
                return count == 8 ? "IPv6" : "Neither";
            }
        }
    }

    static class Solution65 {
        public int singleNumber(int[] nums) {
            int res = nums[0];
            for (int i = 1; i < nums.length; i++) {
                res ^= nums[i];
            }
            return res;
        }
    }

    static class Solution66 {
        public static int[] findDiagonalOrder(int[][] mat) {
            int m = mat.length;
            int n = mat[0].length;
            int[] res = new int[m * n];
            int count = 0;
            for (int i = 0; i < m + n - 1; ++i) {
                if ((i & 1) == 0) {
                    int row = i < m ? i : m - 1;
                    int col = i < m ? 0 : i - m + 1;
                    while (row >= 0 && col < n) {
                        res[count++] = mat[row--][col++];
                    }
                } else {
                    int row = i < n ? 0 : i - n + 1;
                    int col = i < n ? i : n - 1;
                    while (col >= 0 && row < m) {
                        res[count++] = mat[row++][col--];
                    }
                }
            }
            return res;
        }
    }

    class Solution67 {
        public int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
            int length = intervals.length;
            int index = 0;
            int rangeL = intervals[0][0];
            int rangeR = intervals[0][1];
            for (int i = 1; i < length; ++i) {
                if (intervals[i][0] <= rangeR) {
                    rangeR = Math.max(rangeR, intervals[i][1]);
                    intervals[index][1] = rangeR;
                } else {
                    index++;
                    rangeL = intervals[i][0];
                    rangeR = intervals[i][1];
                    intervals[index][0] = rangeL;
                    intervals[index][1] = rangeR;

                }
            }
            int[][] res = new int[index][2];
            res = Arrays.copyOf(intervals, index + 1);
            return res;
        }
    }

    class Solution68 {
        public int minDistance(String word1, String word2) {
            int m = word1.length();
            int n = word2.length();
            int[][] dp = new int[m + 1][n + 1];
            for (int i = 1; i <= m; ++i) {
                dp[i][0] = dp[i - 1][0] + 1;
            }
            for (int i = 1; i <= n; ++i) {
                dp[0][i] = dp[0][i - 1] + 1;
            }
            for (int i = 1; i <= m; ++i) {
                for (int j = 1; j <= n; ++j) {
                    int one = word1.charAt(i - 1) == word2.charAt(j - 1) ? dp[i - 1][j - 1] : dp[i - 1][j - 1] + 1;
                    int two = dp[i][j - 1] + 1;
                    int three = dp[i - 1][j] + 1;
                    dp[i][j] = Math.min(Math.min(one, two), three);
                }
            }
            return dp[m][n];
        }
    }


    class Solution69 {
        public int lastRemaining(int n, int m) {
            if (n == 1) {
                return 0;
            }
            return (lastRemaining(n - 1, m) + m) % n;
        }
    }

    class Solution70 {
        boolean isBalance = true;

        public boolean isBalanced(TreeNode root) {
            process(root);
            return isBalance;
        }

        public int process(TreeNode root) {
            if (root == null) return 0;
            int left = process(root.left);
            int right = process(root.right);
            if (Math.abs(left - right) >= 2) isBalance = false;
            return Math.max(left, right) + 1;
        }
    }

    class Solution71 {
        public int firstMissingPositive(int[] nums) {
            int len = nums.length;
            for (int i = 0; i < nums.length; ++i) {
                while (nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) {
                    swap(nums, nums[i] - 1, i);
                }
            }
            for (int i = 0; i < len; ++i) {
                if (nums[i] != i + 1) {
                    return i + 1;
                }
            }
            return len + 1;
        }

        public void swap(int[] nums, int m, int n) {
            int temp = nums[m];
            nums[m] = nums[n];
            nums[n] = temp;
        }
    }

    class Solution72 {
        public void moveZeroes(int[] nums) {
            int leftEdge = -1;
            int l = 0;
            int r = nums.length;
            while (l < r) {
                if (nums[l] != 0) {
                    swap(nums, ++leftEdge, l);
                }
                l++;
            }
        }

        public void swap(int[] nums, int m, int n) {
            int temp = nums[m];
            nums[m] = nums[n];
            nums[n] = temp;

        }

    }

    class Solution73 {
        public void rotate(int[][] matrix) {
            int m = matrix.length;
            for(int i = 0;i<m/2;++i){
                for(int j = 0;j<m;++j){
                    int tmp = matrix[i][j];
                    matrix[i][j] = matrix[m-i-1][j];
                    matrix[m-i-1][j] = tmp;
                }
            }
            for(int i = 0;i<m;++i){
                for(int j = 0;j<=i;++j){
                    int tmp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = tmp;
                }
            }
        }
    }

    class Solution74{
        public int longestValidParentheses(String s) {
            int left = 0;
            int right = 0;
            int res = 0;
            for (int i = 0; i < s.length(); i++) {
                if(s.charAt(i)=='('){
                    left++;
                }else {
                    right++;
                }
                if(left==right){
                    res = Math.max(res,2*right);
                }
                if(right>left){
                    left=right=0;
                }
            }
            left=right=0;
            for (int i = s.length()-1;i>=0;--i){
                if(s.charAt(i)=='('){
                    left++;
                }else {
                    right++;
                }
                if(left==right){
                    res = Math.max(res,2*left);
                }
                if(right<left){
                    left=right=0;
                }
            }
            return res;
        }
    }
    class Solution75{

        public boolean isPalindrome(ListNode head) {
            if(head==null)return false;
            ListNode fast = head;
            ListNode slow = head;
            while(fast.next!=null&&fast.next.next!=null){
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode pre = null;
            while(slow!=null){
                ListNode next = slow.next;
                slow.next=pre;
                pre = slow;
                slow = next;
            }
            while(pre!=null&&head!=null){
                if(pre.val!=head.val)return false;
                pre = pre.next;
                head = head.next;
            }
            return true;
        }
    }
    class Solution76 {
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            if (root == null) return res;
            process(root, targetSum, new ArrayList<Integer>());
            return res;

        }

        public void process(TreeNode root, int targetSum, List<Integer> path) {
            if (root == null) return;
            path.add(root.val);
            if (root.left == null && root.right == null && root.val == targetSum) {
                res.add(new ArrayList<Integer>(path));
            } else {
                process(root.left, targetSum - root.val, path);
                process(root.right, targetSum - root.val, path);
            }
            path.remove(path.size()-1);
        }
    }
    class Solution77 {
        public TreeNode invertTree(TreeNode root) {
            if(root==null)return root;
            TreeNode left = root.left;
            TreeNode rigth = root.right;
            root.left = rigth;
            root.right = left;
            invertTree(root.left);
            invertTree(root.right);
            return root;
        }
    }

    class Solution78 {
        public int minDepth(TreeNode root){
            if(root==null)return 0;
            if(root.left==null&&root.right==null)return 1;
            int left = minDepth(root.left);
            int right = minDepth(root.right);
            if(left==0)return right+1;
            if(right==0)return left+1;
            return Math.min(left,right)+1;
        }

    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(Solution66.findDiagonalOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}})));


    }
}
