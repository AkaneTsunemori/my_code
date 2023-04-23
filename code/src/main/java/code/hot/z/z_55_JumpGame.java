package code.hot.z;

public class z_55_JumpGame {
    public  boolean canJump(int[] nums) {
        int max = nums[0];
        for(int i = 1;i<nums.length;++i){
            max = Math.max(max,nums[i]+i);
            if(max>=nums.length-1)return  true;
        }
        return false;
    }
}
