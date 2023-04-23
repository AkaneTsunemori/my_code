package code.hot.g;

public class a_45_1143_longest_common_subsequence {
    public  int longestCommonSubsequence(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int M = chars1.length;
        int N = chars2.length;
        int[][] dp = new int[M][N];
        dp[0][0] = chars1[0]==chars2[0]?1:0;
        for (int i = 1; i < M; i++) {
            dp[i][0] = chars1[i]==chars2[0]?1:dp[i-1][0];
        }
        for(int i = 1;i<N;++i){
            dp[0][i] = chars1[0]==chars2[i]?1:dp[0][i-1];
        }
        for(int i = 1;i<M;++i){
            for(int j = 1;j<N;++j){
                dp[i][j] =chars1[i]==chars2[j]?1+dp[i-1][j-1]:Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        return dp[M-1][N-1];
    }
}
