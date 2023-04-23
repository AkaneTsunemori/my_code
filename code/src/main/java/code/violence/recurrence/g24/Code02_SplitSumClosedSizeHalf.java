package code.violence.recurrence.g24;

public class Code02_SplitSumClosedSizeHalf {
    public static int right(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        if ((arr.length & 1) == 0) {
            return process(arr, 0, arr.length / 2, sum / 2);
        } else {
            return Math.max(process(arr, 0, arr.length / 2, sum / 2), process(arr, 0, arr.length / 2 + 1, sum / 2));
        }
    }

    public static int process(int[] arr, int index, int pieces, int rest) {
        if(pieces==0){
            return 0;
        }
        if (index == arr.length) {
            return -1;
        }
        int nonChoose = process(arr, index + 1, pieces, rest);
        int next = -1;
        if (rest - arr[index] >= 0) {
            next = process(arr, index + 1, pieces - 1, rest - arr[index]);
        }
        int choose = next == -1 ? next : arr[index] + next;
        return Math.max(nonChoose,choose);
    }

    public static int dp2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        sum >>= 1;
        int N = arr.length;
        int M = (arr.length + 1) >> 1;
        int[][][] dp = new int[N][M + 1][sum + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= M; j++) {
                for (int k = 0; k <= sum; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int k = 0; k <= sum; k++) {
                dp[i][0][k] = 0;
            }
        }
        for (int k = 0; k <= sum; k++) {
            dp[0][1][k] = arr[0] <= k ? arr[0] : Integer.MIN_VALUE;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= Math.min(i + 1, M); j++) {
                for (int k = 0; k <= sum; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (k - arr[i] >= 0) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - 1][k - arr[i]] + arr[i]);
                    }
                }
            }
        }
        return Math.max(dp[N - 1][M][sum], dp[N - 1][N - M][sum]);
    }

    // for test
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    public static int splitSumClosedSizeHalfDp(int[] arr){
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        int index = arr.length;
        int pieces = (index+1)>>1;
        int rest = sum>>1;
        int [][][]dp =new int[index+1][pieces+1][rest+1];
        for (int i = 0 ;i<=index;++i){
            for(int j =  0;j<=pieces;++j){
                for (int k = 0;k<=rest;++k){
                    dp[i][j][k] = -1;
                }
            }
        }

        for(int k = 0;k<=rest;++k){
            for(int i = 0;i<=index;++i){
                dp[i][0][k]=0;
            }
        }
        for(int i = index-1;i>=0;--i){
            for(int j= 1;j<=pieces;++j){
                for (int k =rest;k>=0;--k){
                    int nonChoose = dp[i+1][j][k];
                    int next = k>=arr[i]?dp[i+1][j-1][k-arr[i]]:-1;
                    int choose = next==-1? next: next+arr[i];
                    dp[i][j][k] = Math.max(nonChoose,choose);
                }
            }
        }
        if ((arr.length & 1) == 0) {
            return dp[0][arr.length / 2][rest];
        } else {
            return Math.max(dp[0][arr.length / 2][rest], dp[0][(arr.length / 2) + 1][rest]);
        }
    }

    // for test
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = right(arr);
            int ans2 = splitSumClosedSizeHalfDp(arr);
            int ans3 = dp2(arr);
            if (
                    ans1 != ans2 ||
                            ans1 != ans3) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
