package code.hot.b;

/***
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 */
public class a004_215_FindKthLargest {

    public int findKthLargest(int[] nums, int k) {
        return process(nums, k,0,nums.length-1);
    }
    public int process(int[] arr, int k ,int l,int r){
        int index = partitionResverse(arr, l, r);
        if(index==k)return arr[index];
        if(index>k){
            return process(arr,k,l,k-1);
        }else {
            return process(arr,k,k+1,r);
        }
    }
    public static int partitionResverse(int[] arr, int l,int r){
        int leftEdge = l-1;
        while(l<r){
            if(arr[l]>arr[r]){
                swap(arr,++leftEdge,l);
            }
            l++;
        }
        swap(arr,++leftEdge,r);
        return leftEdge;
    }
    public static void swap(int[] arr, int l,int r){
        int temp = arr[l];
        arr[l ] = arr[r];
        arr[r] =temp ;
    }

    public static void main(String[] args) {
        System.out.println(partitionResverse(new int[]{6,7,8,9,10,5,2,3,4,5}, 0, 9));
    }

}
