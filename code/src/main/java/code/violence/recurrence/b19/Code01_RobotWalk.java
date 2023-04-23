package code.violence.recurrence.b19;

public class Code01_RobotWalk {
    public static void main(String[] args) {
        System.out.println(ways1(10, 1, 5, 16));
        System.out.println(ways2(10, 1, 5, 16));
        System.out.println(ways3(10, 1, 5, 16));
        System.out.println(wayfinal(10, 1, 5, 16));
        System.out.println(wayFinalDp(10, 1, 5, 16));
    }
    public static int wayFinalDp(int N, int start, int aim, int k) {
//        变化的是start和k
        int [][] dp = new int[N+1][k+1];
        for (int i = 1;i<=N;++i){
            dp[i][0]=i==aim?1:0;
        }
        for(int j = 1;j<=k;++j){
//            因为只有dp[aim][0]是有效的, 如果aim==2那么dp[2][j-1]就是有效的, 否则就是0
            dp[1][j] = dp[2][j-1];
            dp[N][j] = dp[N-1][j-1];
            for (int i =2;i<N;++i){
                dp[i][j] = dp[i+1][j-1]+dp[i-1][j-1];
            }
        }
        return dp[start][k];

    }
    public static int wayfinal(int N, int start, int aim, int k) {
        if(k==0){
            return start==aim?1:0;
        }else {
            if(start==1){
                return wayfinal(N,2,aim,k-1);
            }else if(start==N){
                return wayfinal(N,N-1,aim,k-1);
            }else {
                return wayfinal(N,start+1,aim,k-1)+ wayfinal(N,start-1,aim,k-1);
            }
        }
    }
    public static  int ways1(int N , int start,int aim,int k ){
        return  process1(N,start,aim,k);
    }
    /**
     * 如何优化?
     * aim和N, 没有变化,之和cur, rest有关, 那么对于cur和rest作为参数和process结果是对应的
     * 但是以上过程存在重复计算相同的cur,rest做为参数的process过程
     */
    public static int process1(int N, int cur , int aim,int rest){
        if(rest==0){
            return cur==aim?1:0;
        }else{
            if(cur==1){
                return process1( N, cur+1 ,  aim, rest-1);
            }else if(cur==N){
                return process1( N, cur-1 ,  aim, rest-1);
            }else{
                return process1( N, cur+1 ,  aim, rest-1)+process1( N, cur-1 ,  aim, rest-1);
            }
        }
    }


    /**
     * 增加缓存方式优化
     * cur范围: 1~N
     * rest范围: 0~K
     * @return
     */
    public static  int ways2(int N , int start,int aim,int k ) {
        int dp[][] = new int[N+1][k+1];
        for(int i = 0;i<N+1;++i){
            for (int j = 0;j<k+1;++j){
                dp[i][j] = -1;
            }
            dp[i][0] = 0;
        }
        dp[aim][0] = 1;
        return process2(N,start,aim,k,dp);

    }
    public static int process2(int N, int cur , int aim,int rest,int [][] dp){
        if(dp[cur][rest]!=-1){
            return dp[cur][rest];
        }else{
            if(cur==1){
                dp[1][rest] =  process2( N, 2 ,  aim, rest-1,dp);
                return dp[1][rest];
            }else if(cur==N){
                dp[N][rest] = process2( N, N-1 ,  aim, rest-1,dp);
                return dp[N][rest];
            }else{
                dp[cur][rest] =  process2( N, cur+1 ,  aim, rest-1,dp)+process2( N, cur-1 ,  aim, rest-1,dp);
                return dp[cur][rest];
            }
        }
    }
    public static  int ways3(int N , int start,int aim,int k ) {
        int dp[][] = new int[N+1][k+1];
        dp[aim][0] = 1;
//        rest为0时仅有一个值是有效的计dp[aim][0]
        for(int rest = 1;rest<k+1;++rest){
//            因为只有dp[aim][0]是有效的, 如果aim==2那么dp[2][j-1]就是有效的, 否则就是0
            dp[1][rest] = dp[2][rest-1];
            dp[N][rest] = dp[N-1][rest-1];
            for(int cur =2;cur<N;++cur ){
                dp[cur][rest] = dp[cur-1][rest-1]+dp[cur+1][rest-1];
            }
        }
        return dp[start][k];

    }

}
