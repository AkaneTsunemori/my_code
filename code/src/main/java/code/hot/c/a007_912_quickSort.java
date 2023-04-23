package code.hot.c;

import java.util.Arrays;

public class a007_912_quickSort {
    public int[] sortArray(int[] nums) {
        process(nums,0,nums.length-1);
        return nums;
    }
    public  void process(int[] arr, int l,int r){
        if(l>=r){
            return;
        }
        int m = quickPartition(arr, l,r);
        process(arr,l,m-1 );
        process(arr,m+1,r);
    }
    public  int  quickPartition(int[] arr, int l ,int r){
//        attention
        int leftEdge = l-1;
        int random =l+ (int) (Math.random() * (r - l));
        swap(arr,r,random);
        int cur = l;
        while(cur<r){
            if(arr[cur]<arr[r]){
                swap(arr,++leftEdge,cur);
            }
            cur++;
        }
        swap(arr,++leftEdge,r);
        return leftEdge;
    }

    public  void swap(int[] arr, int l,int r){
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] =temp ;
    }

    public static void main(String[] args) {
        a007_912_quickSort x = new a007_912_quickSort();
        System.out.println(Arrays.toString(x.sortArray(new int[]{2, 152, 3, 123, 5, 342, 12, 3532})));
    }
}
