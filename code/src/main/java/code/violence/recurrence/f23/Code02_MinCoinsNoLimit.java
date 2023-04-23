package code.violence.recurrence.f23;


//arr是一个货币数组, 都不相同, 求构建和为aim的最少货币张数
public class Code02_MinCoinsNoLimit {
    public static int minCoins(int[] arr, int aim) {
        return processMinCoins(arr, 0, aim);
    }
    public static int processMinCoins(int[] arr, int index, int rest){
        if(rest==0){
            return 0;
        }
        if(index==arr.length){
            return Integer.MAX_VALUE;
        }
        int res = Integer.MAX_VALUE;
        for(int i = 0;rest-i*arr[index]>=0;++i){
            int next = processMinCoins(arr, index + 1, rest - i * arr[index]);
            res = Math.min(res,next==Integer.MAX_VALUE?next:next+i);
        }
        return res;
    }
    public static int minCoinsDp(int[] arr, int aim){
        int N = arr.length;
        int dp[][] = new int[N+1][aim+1];
        for(int i= 1;i<aim+1;++i){
            dp[N][i] = Integer.MAX_VALUE;
        }
        for(int i = N-1;i>=0;--i){
            for(int j = 1;j<aim+1;++j){
                dp[i][j] =Integer.MAX_VALUE;
                for (int k = 0;j - k * arr[i] >= 0; ++k){
                    dp[i][j] = Math.min(dp[i][j],dp[i+1][j-k*arr[i]]==Integer.MAX_VALUE?Integer.MAX_VALUE:dp[i+1][j-k*arr[i]]+k);
                }
            }
        }
        return dp[0][aim];
    }

    public static int dp2(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int j = 1; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0
                        && dp[index][rest - arr[index]] != Integer.MAX_VALUE) {
                    dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - arr[index]] + 1);
                }
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < N; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            } while (has[arr[i]]);
            has[arr[i]] = true;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 300000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = minCoins(arr, aim);
            int ans2 = minCoinsDp(arr, aim);
            int ans3 = dp2(arr, aim);
            if (
                    ans1 != ans2 ||
                            ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
//                System.out.println(ans2);
                break;
            }
        }
        System.out.println("功能测试结束");
    }


}
