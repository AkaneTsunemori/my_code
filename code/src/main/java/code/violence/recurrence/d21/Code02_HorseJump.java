package code.violence.recurrence.d21;

public class Code02_HorseJump {
    public static int jump(int a, int b, int k) {
        return  process(0,0,a,b,k);
    }
    public static int process(int x,int y, int a,int b, int rest){
        if(x<0||x>9||y<0||y>8){
            return 0;
        }
        if(rest==0){
            return x==a&&y==b?1:0;
        }

        int ways = process(x+2,y+1,a,b,rest-1);
        ways+= process(x+2,y-1,a,b,rest-1);
        ways+= process(x+1,y+2,a,b,rest-1);
        ways+= process(x+1,y-2,a,b,rest-1);
        ways+= process(x-2,y+1,a,b,rest-1);
        ways+= process(x-2,y-1,a,b,rest-1);
        ways+= process(x-1,y+2,a,b,rest-1);
        ways+= process(x-1,y-2,a,b,rest-1);
        return ways;
    }
    //将dp表的范围扩大2,对于越界的情况会自动返回0
    public static int dp(int a,int b, int k ){
        int [][][]dp = new int[14][13][k+1];
        dp[a+2][b+2][0] =1;
        for(int l = 1;l<=k;++l){
            for(int i = 2;i<12;++i){
                for (int j = 2;j<11;++j){
                    dp[i][j][l] = dp[i+2][j+1][l-1]+dp[i+2][j-1][l-1]+
                            dp[i+1][j+2][l-1]+dp[i+1][j-2][l-1]+
                            dp[i-2][j+1][l-1]+dp[i-2][j-1][l-1]+
                            dp[i-1][j+2][l-1]+dp[i-1][j-2][l-1];
                }
            }
        }
        return dp[2][2][k];
    }
    public static void main(String[] args) {
        System.out.println(jump(7, 7, 10));
        System.out.println(dp(7, 7, 10));
    }
}
