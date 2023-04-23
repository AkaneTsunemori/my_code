package code.hot.d;

import java.util.ArrayList;
import java.util.List;

public class a_021_046_permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>>res = new ArrayList<>();
        process(nums,0,res);
        return res;
    }
    public void process(int[] nums,int index,List<List<Integer>>res ){
        if(index==nums.length-1) {
            List<Integer> tempRes = new ArrayList<>();
            for (int i = 0; i < nums.length; ++i) {
                tempRes.add(nums[i]);
            }
            res.add(tempRes);
        }
            for (int i = index;i<nums.length;++i){
                swap(nums,i,index);
                process(nums,index+1,res);
                swap(nums,i,index);
            }

    }
    public void swap(int[] arr, int m,int n ){
        int temp = arr[m];
        arr[m ]= arr[n];
        arr[n] =temp;
    }
}
