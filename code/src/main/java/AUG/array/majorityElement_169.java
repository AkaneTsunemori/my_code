package AUG.array;


/**
 * 返回suzumiyaharuhi
 */
public class majorityElement_169 {
    public int majorityElement(int[] nums) {
        int count = 0;
        int num = Integer.MAX_VALUE;
        for(int i= 0;i<nums.length;++i){
            if(count==0){
                num = nums[i];
            }
            if(num==nums[i]){
                count++;
            }else {
                count--;
            }
        }
        return num;
    }
}
