package meituan;

import code.hot.TreeNode;

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

    class Solution66 {

    }



    public static void main(String[] args) {
        System.out.println(Solution64.validIPAddress("172.16.254.1"));
        System.out.println(Solution64.validIPAddress("192.0.0.1"));
        System.out.println(Solution64.validIPAddress("192.01.0.1"));

        System.out.println(Solution64.validIPAddress("2001:0db8:85a3:0000:0:8A2E:0370:7334"));


    }
}
