package code.violence.recurrence.f23;


//拆分数字, 切后面的数字不可小于前面的数字,求给定数字n的拆分方式
public class Code03_SplitNumber {
    public static int ways(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return process(1, n);
    }

    public static int ways2(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return process2(1, n);
    }

    private static int process(int part, int rest) {
        if (rest == part) {
            return 1;
        }
        int res = 0;
        for (int i = part; i <= rest - i; ++i) {
            res += process(i, rest - i);
        }
//        补充rest==0的情况
        return res + 1;
    }

    public static int dp1(int n) {
        int N = n;
        int[][] dp = new int[N + 1][N + 1];
        for (int i = 1; i <= N; ++i) {
            dp[i][i] = 1;
        }
        for (int j = 1; j <= N; ++j) {
            for (int i = 1; i <= N; ++i) {
                int res = 0;
                for (int k = i; k <= j - k; ++k) {
                    res+= dp[k][j - k];
                }
                dp[i][j]=++res;
            }
        }
        return dp[1][n];

    }


    public static int process2(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        }
        int ways = 0;
        for (int first = pre; first <= rest; first++) {
            ways += process2(first, rest - first);
        }
        return ways;
    }


    public static int dp2(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                dp[pre][rest] = dp[pre + 1][rest];
                dp[pre][rest] += dp[pre][rest - pre];
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        int test = 39;
        System.out.println(ways(test));
        System.out.println(ways2(test));
        System.out.println(dp2(test));
        System.out.println(dp1(test));

    }


}
