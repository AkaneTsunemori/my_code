package code.hot.f;

public class a_36_4_median_of_two_sorted_arrays {
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


    public int findKthNum(int[] arr1, int[] arr2, int kth) {
        int l1 = arr1.length;
        int l2 = arr2.length;
        int[] arrL = l1 > l2 ? arr1 : arr2;
        int[] arrS = l1 < l2 ? arr1 : arr2;
        int lenL = arrL.length;
        int lenS = arrS.length;
        if (kth <= lenS) {
            return findUpMidNumOfArrays(arrS, arrL, 0, kth - 1, 0, kth - 1);
        } else if (kth < lenL) {
            if (arrL[kth - lenS - 1] >= arrS[lenS - 1]) {
                return arrL[kth - lenS - 1];
            } else {
                return findUpMidNumOfArrays(arrS, arrL, 0, lenS-1, kth - lenS, kth-1);
            }
        } else {
            if(arrL[kth-lenS-1]>=arrS[lenS-1]){
                return arrL[kth-lenS-1];
            }else if(arrS[kth-lenL-1]>=arrL[lenL-1]){
                return arrS[kth-lenL-1];
            }
            return findUpMidNumOfArrays(arrS,arrL,kth-lenL,lenS-1,kth-lenS,lenL-1);
        }
    }


    //返回长度相等的两个有序数组的上中位数
    public int findUpMidNumOfArrays(int[] nums1, int[] nums2, int L1, int R1, int L2, int R2) {
        while (L1 < R1) {
            int mid1 = L1 + ((R1 - L1) >> 1);
            int mid2 = L2 + ((R2 - R1) >> 1);
            if (nums1[mid1] == nums2[mid2]) {
                return nums1[mid1];
            }
            boolean even = ((R1 - L1) & 1) == 0;
            if (even) {
                if (nums1[mid1] > nums2[mid2]) {
                    R1 = mid1;
                    L2 = mid2 + 1;
                } else {
                    R2 = mid2;
                    L1 = mid1 + 1;
                }
            } else {
                if (nums1[mid1] > nums2[mid2]) {
                    if (nums2[mid2] > nums1[mid1 - 1]) {
                        return nums2[mid2];
                    } else {
                        R1 = mid1 - 1;
                        L2 = mid2 + 1;
                    }
                } else {
                    if (nums1[mid1] > nums2[mid2 - 1]) {
                        return nums1[mid1];
                    } else {
                        R2 = mid2 - 1;
                        L1 = mid1 + 1;
                    }
                }
            }
        }
        return nums1[L1];
    }


}
