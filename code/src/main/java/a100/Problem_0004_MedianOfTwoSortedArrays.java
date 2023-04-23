package a100;

public class Problem_0004_MedianOfTwoSortedArrays {


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size = nums1.length+nums2.length;
        boolean even  = (size&1)==0;
        if(nums1.length!=0&&nums2.length!=0){
            if(even){
                return (double)(findKthNum(nums1,nums2,size/2)+findKthNum(nums1,nums2,size/2+1))/2;
            }else {
                return findKthNum(nums1,nums2,size/2+1);
            }
        }else if(nums1.length!=0){
            if(even){
                return (double) (nums1[(size-1)/2]+nums1[size/2])/2;
            }else {
                return nums1[(size)/2];
            }
        }else if(nums2.length!=0){
            if(even){
                return (double) (nums2[(size-1)/2]+nums2[size/2])/2;
            }else {
                return nums2[(size)/2];
            }
        }else {
            return 0;
        }
    }

    /**
     * 返回两个有序但不一定等长的数据 合并排序后的第k小的值
     *
     * @param arr1
     * @param arr2
     * @param kth
     * @return
     */
    public static int findKthNum(int[] arr1, int[] arr2, int kth) {
        int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
        int[] shorts = arr1.length < arr2.length ? arr1 : arr2;
        int l = longs.length;
        int s = shorts.length;
        if (kth <= s) {
            return getUpMedian(shorts, 0, kth - 1, longs, 0, kth - 1);
        }
        if (kth > l) {
            if (shorts[kth - l - 1] >= longs[l - 1]) {
                return shorts[kth - l - 1];
            }
            if (longs[kth - s - 1] >= shorts[s - 1]) {
                return longs[kth - s - 1];
            }
            return getUpMedian(shorts, kth - l, s - 1, longs, kth - s, l - 1);
        }
        if (longs[kth - s - 1] >= shorts[s - 1]) {
            return longs[kth - s - 1];
        }
        return getUpMedian(shorts, 0, s - 1, longs, kth - s, kth - 1);

    }

    /**
     * arr1 和arr2 有序且长度一致, 返回两个合并后的上中位数
     *
     * @param A
     * @param B
     * @return
     */
    public static int getUpMedian(int[] A, int L1, int R1, int[] B, int L2, int R2) {
        int mid1 = 0;
        int mid2 = 0;
        while (L1 < R1) {
            mid1 = (L1 + R1) / 2;
            mid2 = (L2 + R2) / 2;
            if (A[mid1] == B[mid2]) {
                return A[mid1];//上中位数相等直接返回
            }
            //偶数长度
            if (((R1 - L1 + 1) & 1) == 0) {
                if (A[mid1] > B[mid2]) {
                    R1 = mid1;
                    L2 = mid2 + 1;
                } else {
                    R2 = mid2;
                    L1 = mid1 + 1;
                }
            }//奇数长度
            else {
                if (A[mid1] > B[mid2]) {
                    if (B[mid2] >= A[mid1 - 1]) {
                        return B[mid2];
                    }
                    R1 = mid1 - 1;
                    L2 = mid2 + 1;
                } else {
                    if (A[mid1] > B[mid2 - 1]) {
                        return A[mid1];
                    }
                    R2 = mid2 - 1;
                    L1 = mid1 + 1;
                }

            }
        }
        return Math.min(A[L1], B[L2]);
    }
}
