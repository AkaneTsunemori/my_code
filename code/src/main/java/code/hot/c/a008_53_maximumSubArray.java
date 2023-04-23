package code.hot.c;

public class a008_53_maximumSubArray {
    public static int maxSubArray(int[]nums){
        int res = nums[0];
        int edg = nums[0];
        for(int i= 1;i<nums.length;++i){
            edg = Math.max(nums[i],edg+nums[i]);
            res = Math.max(edg,res);
        }
        return Math.max(edg,res);
    }
//    public static int maxSubArray2(int[] nums) {
//        int res = nums[0];
//        int dp[] = new int[nums.length];
//        dp[0] = nums[0];
//        for(int i =1;i<nums.length;++i){
//            dp[i] = Math.max(dp[i-1]+nums[i],nums[i]);
//            res = Math.max(res,dp[i]);
//        }
//        return res;
//    }

    public static int maxSubArray2(int[] nums) {
        int  res = nums[0];
        int sum = 0;
        for (int num : nums) {
            sum = Math.max(sum+num,num);
            res = Math.max(res,sum);
        }
        return res;
    }
    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println(maxSubArray(new int[]{4,-1,2,1}));
        System.out.println(maxSubArray(new int[]{0}));
        System.out.println(maxSubArray(new int[]{-100}));
        System.out.println(maxSubArray(new int[]{-1,-2}));
        System.out.println(maxSubArray2(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println(maxSubArray2(new int[]{4,-1,2,1}));
        System.out.println(maxSubArray2(new int[]{0}));
        System.out.println(maxSubArray2(new int[]{-100}));
        System.out.println(maxSubArray2(new int[]{-1,-2}));
    }
}
