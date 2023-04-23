package code.violence.recurrence.c20;

/**
 * 最长公共子序列长度
 */
public class Code04_LongestCommonSubsequence {
    public static int longestCommonSubsequence(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        return process1(chars1, chars2, chars1.length - 1, chars2.length - 1);
    }

    public static int process1(char[] chars1, char[] chars2, int i, int j) {
        if (i == 0 && j == 0) {
            return chars1[i] == chars2[j] ? 1 : 0;
        } else if (i == 0) {
            return chars1[0] == chars2[j] ? 1 : process1(chars1, chars2, 0, j - 1);
        } else if (j == 0) {
            return chars1[i] == chars2[0] ? 1 : process1(chars1, chars2, i - 1, 0);
        } else {
            return chars1[i] == chars2[j] ?
                    1 + process1(chars1, chars2, i - 1, j - 1)
                    : Math.max(process1(chars1, chars2, i - 1, j), process1(chars1, chars2, i, j - 1));
        }
    }

    public static int longestCommonSubsequence2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int M = chars1.length;
        int N = chars2.length;
        int[][] dp = new int[M][N];
        dp[0][0] = chars1[0] == chars2[0] ? 1 : 0;
        for (int i = 1; i < M; ++i) {
            dp[i][0] = chars1[i] == chars2[0] ? 1 : dp[i - 1][0];
        }
        for (int j = 1; j < N; ++j) {
            dp[0][j] = chars1[0] == chars2[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < M; ++i) {
            for (int j = 1; j < N; ++j) {
                dp[i][j] = chars1[i] == chars2[j] ? 1 + dp[i - 1][j - 1] : Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[M - 1][N - 1];
    }
    public static int longestCommonSubsequence3(String s1, String s2) {
        char[] charsS1 = s1.toCharArray();
        char[] charsS2 = s2.toCharArray();
        return processLongestCommonSubsequence(charsS1,charsS2,0,0);

    }
    public static int processLongestCommonSubsequence(char[]charsS1,char[]charsS2,int p1,int p2){
        if(p1==charsS1.length-1 && p2==charsS2.length-1){
            return charsS1[p1]==charsS2[p2]?1:0;
        }else if(p1==charsS1.length-1){
            return charsS1[p1]==charsS2[p2]?1:processLongestCommonSubsequence(charsS1,charsS2,p1,p2+1);
        }else if(p2==charsS2.length-1){
            return charsS1[p1]==charsS2[p2]?1:processLongestCommonSubsequence(charsS1,charsS2,p1+1,p2);
        }else {
            return charsS1[p1]==charsS2[p2]?1+processLongestCommonSubsequence(charsS1,charsS2,p1+1,p2+1):Math.max(
                    processLongestCommonSubsequence(charsS1,charsS2,p1,p2+1),
                    processLongestCommonSubsequence(charsS1,charsS2,p1+1,p2)
            );
        }
    }
    public static  int longestCommonSubsequenceDp(String s1, String s2){
        char[] charsS1 = s1.toCharArray();
        char[] charsS2 = s2.toCharArray();
        int M = s1.length();
        int N = s2.length();
        int [][] dp = new int[M][N];
        dp[M-1][N-1] = charsS1[M-1]==charsS2[N-1]?1:0;
        for(int i = N-2;i>=0;--i){
            dp[M-1][i] = charsS1[M-1]==charsS2[i]?1:dp[M-1][i+1];
        }
        for (int i = M-2;i>=0;--i){
            dp[i][N-1] = charsS1[i]==charsS2[N-1]?1:dp[i+1][N-1];
        }
        for(int i=M-2;i>=0;--i){
            for (int j = N-2;j>=0;--j){
                dp[i][j] = charsS1[i]==charsS2[j]?1+dp[i+1][j+1]:Math.max(
                        dp[i][j+1],
                        dp[i+1][j]);
            }
        }
        return dp[0][0];

    }

    public static void main(String[] args) {
//        System.out.println(longestCommonSubsequence("mhunuzqrkzsnidwbun", "szulspmhwpazoxijwbq"));
        System.out.println(longestCommonSubsequence2("mhunuzqrkzsnidwbun", "szulspmhwpazoxijwbq"));
//        System.out.println(longestCommonSubsequence3("mhunuzqrkzsnidwbun", "szulspmhwpazoxijwbq"));
        System.out.println(longestCommonSubsequenceDp("mhunuzqrkzsnidwbun", "szulspmhwpazoxijwbq"));


    }
}
