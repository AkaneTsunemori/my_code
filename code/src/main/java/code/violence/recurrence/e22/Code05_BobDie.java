package code.violence.recurrence.e22;

public class Code05_BobDie {
    public static double livePosibility1(int row, int col, int M, int N, int rest) {
        return (double) process(row, col, M, N, rest) / Math.pow(4, rest);
    }

    private static int process(int row, int col, int m, int n, int rest) {
        if(m<0||m==row||n<0||n==col){
            return 0;
        }
        return rest==0?1:process(row,col,m+1,n,rest-1)+
                process(row,col,m-1,n,rest-1)+
                process(row,col,m,n+1,rest-1)+
                process(row,col,m,n-1,rest-1);

    }
    public static double dp(int row, int col, int M, int N, int rest) {
        int[][][]dp =new int[row+3][col+3][rest+1];
        for (int i = 1;i<=row;i++){
            for (int j = 1;j<=col;++j){
                dp[i][j][0] = 1;
            }
        }
        for (int k =1;k<=rest;++k){
            for (int i = 1;i<=row;i++){
                for (int j = 1;j<=col;++j){
                    dp[i][j][k] =dp[i-1][j][k-1]+dp[i+1][j][k-1]+dp[i][j-1][k-1]+dp[i][j+1][k-1];
                }
            }
        }
        return dp[M+1][N+1][rest] / Math.pow(4, rest);

    }

    public static void main(String[] args) {
        System.out.println(livePosibility1(10,10,6,6,10));
        System.out.println(dp(10,10,6,6,10));

    }

}
