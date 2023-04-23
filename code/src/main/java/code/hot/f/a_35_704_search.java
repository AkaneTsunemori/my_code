package code.hot.f;

public class a_35_704_search {
    public int bSearch(int[]nums,int target){
        return bSearchProcess(nums,0,nums.length-1,target);
    }

    private int bSearchProcess(int[] nums, int l, int r,int target) {
        if(l>r)return -1;
        int mid = l+((r-l)>>1);
        if(nums[mid]==target){
            return mid;
        }
        if(nums[mid]<target){
            return bSearchProcess(nums,mid+1,r,target);
        }else{
            return bSearchProcess(nums,l,mid-1,target);
        }
    }
}
