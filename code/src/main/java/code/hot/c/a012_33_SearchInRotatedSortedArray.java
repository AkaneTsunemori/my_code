package code.hot.c;

public class a012_33_SearchInRotatedSortedArray {
    public static void main(String[] args) {
        int[] nums = new int[]{4,5,6,7,9,10,1,2,3};
        for (int num : nums) {
            System.out.println(search(nums,num));
        }
        System.out.println(search(nums,231));

    }
    public  static int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length-1;
        return searchProcess(nums,target, l,r);

    }
    public static int subProcess(int []arr, int target, int l ,int r){
        if(l==r&&arr[l]!=target){
            return -1;
        }
        int mid = l+((r-l)>>1);
        if(arr[mid]==target){
            return mid;
        }else if(arr[l]<arr[mid]){
            if(arr[l]<=target&&target<arr[mid]){
                return subProcess(arr,target,l,mid-1);
            }
            return subProcess(arr,target,mid+1,r);
        }else if(arr[l]==arr[mid]){
            return subProcess(arr,target,mid+1,r);
        } else{
            if(arr[mid]<target&&target<=arr[r]){
                return subProcess(arr,target,mid+1,r);
            }
            return subProcess(arr,target,l,mid-1);
        }
    }
    public  static int searchProcess(int[]nums, int target,int l,int r){
        if(l>r){
            return -1;
        }
        if(l==r&&nums[l]!=target){
            return -1;
        }
        int mid = l+((r-l)>>1);
        if(nums[mid]==target){
            return mid;
        }else if(nums[mid]>nums[l]){
            if(nums[l]<=target&&target<nums[mid]){
                return searchProcess(nums,target,l,mid-1);
            }
            return searchProcess(nums,target,mid+1,r);
        }else if(nums[mid]==nums[l]){
            return nums[mid+1]==target?mid+1:-1;
        }
        else {
            if(nums[mid]<target&&target<=nums[r]){
                return  searchProcess(nums,target,mid+1,r);
            }
            return searchProcess(nums,target,l,mid-1);
        }
    }

}
