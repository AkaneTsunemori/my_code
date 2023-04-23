package code.violence.recurrence.c20;


public class Code01_Knapsack {
    public static int maxValue(int[] w, int[] v, int bag) {
        return process(w, v, bag, 0);
    }

    //递归,对于process(w,v, bag,index),只有bag和index变化,可以缓存起来,遇到相同的bag和index直接取值,也即dp[bag][index]
    public static int process(int[] w, int[] v, int bag, int index) {
        if (index == w.length
//                || bag < 0
        ) {
            return 0;
        } else {
            int p1 = process(w, v, bag, index + 1);
            int p2 = 0;
            if (w[index] <= bag) {
                p2 = v[index] + process(w, v, bag - w[index], index + 1);
            }
            return Math.max(p1, p2);
        }
    }

    //dp 动态规划解
    public static int dp(int[] w, int[] v, int bag) {
        int N = w.length;
        int dp[][] = new int[bag + 1][N + 1];
//        第N行已经填充,所以从N-1开始
        for (int index = N - 1; index >= 0; --index) {
            for (int j = 0; j < bag + 1; ++j) {
                int p1 = dp[j][index + 1];
                int p2 = 0;
                if (w[index] <= j) {
                    p2 = v[index] + dp[j - w[index]][index + 1];
                }
                dp[j][index] = Math.max(p1, p2);
            }
        }
        return dp[bag][0];
    }

    public static int processMaxValue3Dp(int[] w, int[] v, int bag){
        int N = w.length;
        int[][]dp = new int[N+1][bag+1];
        for(int i= N-1;i>=0;--i){
            for (int j = 0;j<=bag;++j){
                int p1 = 0;
                if(j-w[i]>=0){
                    p1 = v[i]+dp[i+1][j-w[i]];
                }
                int p2 = dp[i+1][j];
                dp[i][j]= Math.max(p1,p2);

            }
        }
        return dp[0][bag];
    }


    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7,3,1,7};
        int[] values = {5, 6, 3, 19,12,4,2};
        int bag = 15;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(processMaxValue3Dp(weights, values, bag));
    }
}
