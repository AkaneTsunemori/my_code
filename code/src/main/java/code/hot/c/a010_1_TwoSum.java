package code.hot.c;

import java.util.HashMap;
import java.util.Map;

public class a010_1_TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<nums.length;++i){
            Integer index = map.getOrDefault(target - nums[i],-1);
            if(index!=-1){
                return new int[]{i,index};
            }
            map.put(nums[i],i);
        }
        return new int[]{0,0};
    }
}
