package code.hot.z;

/***
 * 给你一个非负整数数组nums ，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 假设你总是可以到达数组的最后一个位置。
 */
public class z_45_jump_game_ii {
    public int jump(int[] nums) {
//        因为nums的长度可能为1,所以初始位置只能从1开始
        int max = 0;
        int step = 0;
        int end = 0;
//        这个地方i到达num.length-1的时候就可以直接退出了!!!
        for(int i = 0;i<nums.length-1;++i){
//            记录可以到达的最远位置
            max = Math.max(max,nums[i]+i);
//        当i走到上一个记录的可以到达的最远位置是,记录下一步可以到达的最远位置
            if(end==i){
                step++;
                end = max;
            }
        }
        return step;

    }


}
