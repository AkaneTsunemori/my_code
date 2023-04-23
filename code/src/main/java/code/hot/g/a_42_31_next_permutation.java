package code.hot.g;

import java.util.Arrays;

/**
 * 全排列的下一个
 */
public class a_42_31_next_permutation {
    public void nextPermutation(int[] nums) {
        int m = nums.length-1;
        //        12385764
//找到从右到左第一个减小的数,eg 5
        while(m>0&&nums[m-1]>=nums[m]){
            m--;
        }
//        如果已经是最大数,直接倒序
        if(m==0){
            reverse(nums,0,nums.length-1);
            return;
        }
        int n = nums.length-1;
//        从右到左找到第一个比5大的数
        while(n>m-1&&nums[n]<=nums[m-1]){
            n--;
        }
//        交换,然后将m到后面的数逆序
        //12386754
        swap(nums,m-1,n);
        //12386457
        reverse(nums,m,nums.length-1);
    }
    public void reverse(int[]arr,int start,int end){
        while(start<end){
            swap(arr,start,end);
            start++;
            end--;
        }

    }
    public void swap(int[]arr, int m,int n){
        int temp = arr[m];
        arr[m]= arr[n];
        arr[n]=temp;
    }

    public static void main(String[] args) {
        a_42_31_next_permutation a = new a_42_31_next_permutation();
        int[] arr = new int[]{1,2,3,8,5,7,6,4};
        a.nextPermutation(arr);
        System.out.println(Arrays.toString(arr));

    }
}
