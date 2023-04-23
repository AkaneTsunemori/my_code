package code.hot.f;

public class a_34_72_edit_distance {
//    允许插入常数替换
    public static int minDistance(String word1, String word2) {
        if (word1.equals("")) return word2.length();
        if (word2.equals("")) return word1.length();
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        int M = chars1.length;
        int N = chars2.length;
        int[][] dp = new int[M + 1][N + 1];
//        两个都是空字符串他们的距离为0
        dp[0][0] = 0;
        for (int i = 1; i <= M; ++i) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= N; ++i) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= M; ++i) {
            for (int j = 1; j <= N; ++j) {
//                由word1增加一个元素来实现相同
                int one = dp[i - 1][j] + 1;
//                由word2增加一个元素来实现相同
                int two = dp[i][j - 1] + 1;
//                直接替换一个元素实现相同（如果当前的元素不同的话）
                int three = chars1[i - 1] == chars2[j - 1] ? dp[i - 1][j - 1] : dp[i - 1][j - 1] + 1;
                dp[i][j] = Math.min(Math.min(one, two), three);
            }
        }
        return dp[M][N];
    }

    public static void main(String[] args) {
        System.out.println(minDistance("sea", "eat"));
    }
}
