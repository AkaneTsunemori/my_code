package a100;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 *
 *
 */
public class Problem_0015_ThreeSum {
    public static List<List<Integer>> threeSum(int[]nums){
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0;i<nums.length-2;++i){
//            注意点;跳过相同值
            if(i>0&&nums[i]==nums[i-1])continue;
            int target = -nums[i];
            for(int l = i+1, r = nums.length-1;l<r;){
                int sum = nums[l]+nums[r];
                if(sum==target){
                    List<Integer>tempRes = new ArrayList<>();
                    tempRes.add(nums[i]);
                    tempRes.add(nums[l]);
                    tempRes.add(nums[r]);
                    res.add(tempRes);
                    l++;
//                    注意点:跳过相同值
                    while(l>i+1&&nums[l]==nums[l-1]&&l<r)l++;
                }else if(sum>target){
                    r--;
                }else {
                    l++;
                }
            }
        }
        return res;
    }
}
