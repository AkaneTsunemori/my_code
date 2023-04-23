package code.violence.recurrence.e22;

public class Code02_CoinsWayEveryPaperDifferent {
    public static int coinWays(int[] arr, int aim) {
        return process(arr, 0, aim);
    }
    public static int coinWays2(int[] arr, int aim) {
        return process2(arr, 0, aim);
    }
    public static int process2(int[] arr, int index, int rest){
        if(rest<0){
            return 0;
        }
        if(index==arr.length){
            return rest==0?1:0;
        }
        if(rest==0){
            return 1;
        }

        return process2(arr, index+1,rest-arr[index])+process2(arr, index+1,rest);
    }

    public static int dp2(int[]arr, int aim ){
        int N = arr.length;
        int[][]dp = new int[N+1][aim+1];
        for(int i = 0;i<=N;++i){
            dp[i][0] = 1;
        }
        for (int i=N-1;i>=0;--i){
            for(int j = 0;j<=aim;++j){
                dp[i][j] =j-arr[i]<0?dp[i+1][j]:dp[i+1][j-arr[i]]+dp[i+1][j];
            }
        }
        return dp[0][aim];

    }

    // arr[index....] 组成正好rest这么多的钱，有几种方法
    public static int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (index == arr.length) { // 没钱了！
            return rest == 0 ? 1 : 0;
        } else {
            return process(arr, index + 1, rest) + process(arr, index + 1, rest - arr[index]);
        }
    }
    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
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
    public static int dp(int[] arr, int aim) {
        if (aim == 0) {
            return 1;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest] + (rest - arr[index] >= 0 ? dp[index + 1][rest - arr[index]] : 0);
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinWays2(arr, aim);
//            int ans2 = coinWays2(arr, aim);

            int ans2 = dp2(arr, aim);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
