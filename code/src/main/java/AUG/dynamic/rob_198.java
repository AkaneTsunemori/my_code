package AUG.dynamic;

public class rob_198 {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int length = nums.length;
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);

        for (int i = 2; i < nums.length; ++i) {
            //考虑dp[i-1]>=dp[i-2],如果dp[i-1]取了i-1位置的元素,那么必然dp[i-1]>dp[i-2],做如下比较即可, 如果没取,则dp[i-1]==dp[i-2]
            dp[i] = Math.max(nums[i]+dp[i-2],dp[i-1]);
        }
        return dp[length-1];

    }
}
