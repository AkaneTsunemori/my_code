package code.hot.g;

import java.lang.reflect.InvocationTargetException;

/**
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 */
public class a_51_41_first_missing_positive {
    public static int firstMissingPositive(int[] nums)   {
        int len = nums.length;
        for(int i = 0;i<len;++i){
            while(1<=nums[i]&&nums[i]<=len&&nums[i]!=nums[nums[i]-1]){
                swap(nums,i,nums[i]-1);
            }
        }
        for(int i = 0;i<len;++i){
            if(i+1!=nums[i]){
                return i+1;
            }
        }
        return len+1;
    }
    public static void swap(int[] nums , int m,int n){
        int temp = nums[m];
        nums[m] = nums[n];
        nums[n] = temp;
    }

    public  static void main(String[] args) {

    }
}
