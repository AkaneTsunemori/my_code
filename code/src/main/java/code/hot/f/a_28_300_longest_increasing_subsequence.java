package code.hot.f;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 */
public class a_28_300_longest_increasing_subsequence {
    public static int lengthOfLIS(int[] nums) {
        int [] dp = new int[nums.length];
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            int temp = 0;
            for (int j = 0;j<i;++j){
                if(nums[j]<nums[i]){
                    temp = Math.max(temp,dp[j]);
                }
            }
            dp[i] = temp+1;
            res = Math.max(res,dp[i]);
        }
        return res;
    }
    public static int lengthOfLIS2(int[] nums) {
        int res = 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 0;
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{10, 9, 2,  3, 7, 101, 18}));
        System.out.println(lengthOfLIS2(new int[]{10, 9, 2, 3, 7, 101, 18}));

    }

}
