package code.violence.recurrence.d21;

public class Code01_PalindromeSubsequence {
    public static int lpsl1(String s) {
        if(s==null||s.length()==0){
            return 0;
        }
        char[] chars = s.toCharArray();
        return f(chars,0,chars.length-1);

    }
    public static int f(char[] chars, int L,int R){
        if(L==R){
            return 1;
        }else if(L==R-1){
            return chars[L]==chars[R]?2:1;
        }else {
            return chars[L]==chars[R]?2+f(chars,L+1,R-1):Math.max(f(chars,L+1,R),f(chars,L,R-1));
        }

    }
    public static int longestPalindromeSubseqDp(String s){
        if(s==null||s.length()==0){
            return 0;
        }
        char[] chars = s.toCharArray();
        int N = chars.length;
        int[][]dp = new int[N][N];
        for(int i = 0;i<N;++i){
            dp[i][i] = 1;
            if(i>=1){
                dp[i-1][i] = chars[i-1]==chars[i]?2:1;
            }
        }
        for(int col = 2;col<N;++col){
            for(int j = col,i = 0;j<N;++j,++i){
                dp[i][j] = chars[i]==chars[j]?2+dp[i+1][j-1]:Math.max(dp[i+1][j],dp[i][j-1]);
            }
        }
        return dp[0][N-1];

    }
    public static int lpslDp(String s) {
        if(s==null||s.length()==0){
            return 0;
        }
        char[] chars = s.toCharArray();
        int N = chars.length;
        int[][]dp = new int[N][N];
        for (int i = 0;i<N;++i){
            dp[i][i] = 1;
            if(i>=1){
                dp[i-1][i] = chars[i-1]==chars[i]?2:1;
            }
        }
        for(int col = 2;col<N;++col){
            for(int i = 0,j=col;j<N;++i,++j){
                dp[i][j] = chars[i]==chars[j]?2+dp[i+1][j-1]:Math.max(dp[i+1][j],dp[i][j-1]);
            }
        }
        return dp[0][N-1];
    }
    public static int lpsl2(String s) {
        if(s==null||s.length()==0){
            return 0;
        }
        char[] chars = s.toCharArray();
        int N = chars.length;
        return processLpsl1(chars,0,N-1);
    }
    public static int processLpsl1(char[]chars,int L, int R){
        if(L==R){
            return 1;
        }
        if(L==R-1){
            return chars[L]==chars[R]?2:1;
        }
        return chars[L]==chars[R]?2+processLpsl1(chars,L+1,R-1):Math.max(processLpsl1(chars,L+1,R),processLpsl1(chars,L,R-1));
    }

    public static void main(String[] args) {
        System.out.println(longestPalindromeSubseqDp("euazbipzncptldueeuechubrcourfpftcebikrxhybkymimgvldiwqvkszfycvqyvtiwfckexmowcxztkfyzqovbtmzpxojfofbvwnncajvrvdbvjhcrameamcfmcoxryjukhpljwszknhiypvyskmsujkuggpztltpgoczafmfelahqwjbhxtjmebnymdyxoeodqmvkxittxjnlltmoobsgzdfhismogqfpfhvqnxeuosjqqalvwhsidgiavcatjjgeztrjuoixxxoznklcxolgpuktirmduxdywwlbikaqkqajzbsjvdgjcnbtfksqhquiwnwflkldgdrqrnwmshdpykicozfowmumzeuznolmgjlltypyufpzjpuvucmesnnrwppheizkapovoloneaxpfinaontwtdqsdvzmqlgkdxlbeguackbdkftzbnynmcejtwudocemcfnuzbttcoew"));
        System.out.println(lpslDp("euazbipzncptldueeuechubrcourfpftcebikrxhybkymimgvldiwqvkszfycvqyvtiwfckexmowcxztkfyzqovbtmzpxojfofbvwnncajvrvdbvjhcrameamcfmcoxryjukhpljwszknhiypvyskmsujkuggpztltpgoczafmfelahqwjbhxtjmebnymdyxoeodqmvkxittxjnlltmoobsgzdfhismogqfpfhvqnxeuosjqqalvwhsidgiavcatjjgeztrjuoixxxoznklcxolgpuktirmduxdywwlbikaqkqajzbsjvdgjcnbtfksqhquiwnwflkldgdrqrnwmshdpykicozfowmumzeuznolmgjlltypyufpzjpuvucmesnnrwppheizkapovoloneaxpfinaontwtdqsdvzmqlgkdxlbeguackbdkftzbnynmcejtwudocemcfnuzbttcoew"));

    }
}
