package code.hot.c;

public class a_017_88_merge_sorted_array {
    public int[] merge(int[] nums1, int m, int[] nums2, int n) {
        if(n==0)return null;
        int [] res = new int[m+n];
        int cur = 0;
        int i=0,j=0;
        for(;i<m&&j<n;){
            if(nums1[i]<=nums2[j]){
                res[cur++]= nums1[i++];
            }else{
                res[cur++]= nums2[j++];
            }
        }
        while(j<n){
            res[cur++]= nums2[j++];
        }
        while(i<m){
            res[cur++]= nums1[i++];
        }
        for(int k=0;k<m+n;++k){
            nums1[k] = res[k];
        }
        return res;
    }
}
