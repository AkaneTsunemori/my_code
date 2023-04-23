package code.hot.pdd;

import code.hot.ListNode;

import java.util.*;

public class Page1 {

    public static List<Integer> spiralOrder54(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int botton = matrix.length - 1;
        while (left <= right && top <= botton) {
            for (int i = left; i <= right; ++i) {
                res.add(matrix[top][i]);
            }
            for (int i = top + 1; i <= botton; i++) {
                res.add(matrix[i][right]);
            }

            if (right > left && botton > top) {
                for (int i = right - 1; i >= left; --i) {
                    res.add(matrix[botton][i]);
                }
                for (int i = botton - 1; i >= top + 1; --i) {
                    res.add(matrix[i][left]);
                }
            }
            left++;
            right--;
            top++;
            botton--;
        }
        return res;
    }

    public int lengthOfLongestSubstring3(String s) {
        int res = 0;
        char[] chars = s.toCharArray();
        int edge = -1;
        int[] map = new int[128];
        for (int i = 0; i < map.length; i++) {
            map[i] = -1;
        }
        for (int i = 0; i < chars.length; ++i) {
            edge = Math.max(edge, map[chars[i]]);
            res = Math.max(res, i - edge);
            map[chars[i]] = i;
        }
        return res;
    }

    public int[][] merge56(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        List<Integer> resRc = new ArrayList<>();
        int left = intervals[0][0];
        int right = intervals[0][1];
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i + 1][0] <= right && right < intervals[i + 1][1]) {
                right = intervals[i + 1][1];
            } else if (intervals[i + 1][0] > right) {
                resRc.add(left);
                resRc.add(right);
                left = intervals[i + 1][0];
                right = intervals[i + 1][1];
            }
        }
        resRc.add(left);
        resRc.add(right);
        int[][] res = new int[resRc.size() / 2][2];
        for (int i = 0; i < resRc.size(); i++) {
            res[i / 2][1 & i] = resRc.get(i);
        }
        return res;
    }

    public int findKthLargest215(int[] nums, int k) {
        return process(nums, k, 0, nums.length - 1);
    }

    public int process(int[] arr, int k, int l, int r) {
        int index = partitionResverse(arr, l, r);
        if (index == k) return arr[index];
        if (index > k) {
            return process(arr, k, l, k - 1);
        } else {
            return process(arr, k, k + 1, r);
        }
    }

    public static int partitionResverse(int[] arr, int l, int r) {
        int edge = l - 1;
        while (l < r) {
            if (arr[l] > arr[r]) {
                swap(arr, ++edge, l);
            }
            l++;
        }
        swap(arr, ++edge, r);
        return edge;
    }

    public static void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }

    public int maxSubArray53(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
            sum = Math.max(sum + num, num);
            res = Math.max(res, sum);
        }
        return res;
    }

    public int maxProfit121(int[] prices) {
        int min = prices[0];
        int res = 0;
        for (int price : prices) {
            min = Math.min(min, price);
            res = Math.max(res, price - min);
        }
        return res;
    }

    public ListNode reverseList206(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public int compareVersion165(String version1, String version2) {
        int v1 = 0;
        int v2 = 0;
        while (v1 < version1.length() || v2 < version2.length()) {
            int v1Value = 0;
            int v2Value = 0;

            while (v1 < version1.length() && version1.charAt(v1) != '.') {
                v1Value += v1Value * 10 + (version1.charAt(v1++) - '0');
            }
            while (v2 < version2.length() && version2.charAt(v2) != '.') {
                v2Value += v1Value * 10 + (version2.charAt(v2++) - '0');
            }
            if (v1Value != v2Value) {
                return v1Value > v2Value ? 1 : -1;
            }
            v1++;
            v2++;
        }
        return 0;
    }

    public String longestPalindrome5(String s) {
        char[] chars = getManacherCharArray(s);
        int C = -1;
        int R = -1;
        int max = 0;
        int resC = 0;
        int[] arr = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
//            容易忘记和 R-i 求最小值
            arr[i] = i < R ? Math.min(arr[2 * C - i], R - i) : 1;
            while (i - arr[i] >= 0 && i + arr[i] < chars.length && chars[i - arr[i]] == chars[i + arr[i]]) {
                arr[i]++;
            }
            if (i + arr[i] > R) {
                R = i + arr[i];
                C = i;
            }
            if (arr[i] > max) {
                max = arr[i];
                resC = i;
            }
        }
        return String.valueOf(chars).substring(resC - max + 1, resC + max - 1).replace("#", "");


    }

    public static char[] getManacherCharArray(String s) {
        char[] res = new char[s.length() * 2 + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : s.charAt(i / 2);
        }
        return res;
    }

    //输入: nums = [1,1,2,3,3,4,4,8,8]
//输出: 2
//
//
// 示例 2:
//
//
//输入: nums =  [3,3,7,7,10,11,11]
//输出: 10
    public int singleNonDuplicate540(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if ((mid & 1) == 0) {
                mid = mid - 1;
            }
            if (nums[mid - 1] == nums[mid]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return nums[l];
    }

    int index = 0;

    public String decodeString394(String s) {
        return getString(s);
    }

    public String getString(String s) {
        if (index == s.length() || s.charAt(index) == ']') {
            return "";
        }
        String res = "";
        if (Character.isDigit(s.charAt(index))) {
            int repeatTime = getDigit(s);
            //过滤左括号
            index++;
            //递归获取里面的str
            String innerStr = getString(s);
            //过滤右括号
            index++;
            while (repeatTime-- > 0) {
                res += innerStr;
            }
        } else if (Character.isLetter(s.charAt(index))) {
            res += s.charAt(index++);
        }
        //这里记得需要递归调用
        return res + getString(s);
    }

    public int getDigit(String s) {
        int digit = 0;
        while (index < s.length() && Character.isDigit(s.charAt(index))) {
            digit = digit * 10 + (s.charAt(index) - '0');
            index++;
        }
        return digit;
    }

    static List<List<Integer>> combineRes = new ArrayList<>();

    public static List<List<Integer>> combine77(int n, int k) {
        combineProcess(n, k, 0, new ArrayList<>());
        return combineRes;
    }

    public static void combineProcess(int n, int k, int index, List<Integer> path) {
        if (path.size() == k) {
            combineRes.add(new ArrayList<>(path));
        } else {
            for (int i = index + 1; i <= n; i++) {
                path.add(i);
                if (path.size() > k) continue;
                combineProcess(n, k, i, path);
                path.remove(path.size() - 1);
            }
        }
    }

    public int fourSumCount454(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int value = -nums1[i] - nums2[i];
                map.put(value, map.getOrDefault(value, 0) + 1);
            }
        }
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                int value = nums3[i] + nums4[j];
                res += map.getOrDefault(value, 0);
            }
        }
        return res;
    }

    static List<String> ipRes = new ArrayList<>();

    public static List<String> restoreIpAddresses93(String s) {
        ipProcess3(s, 0, new ArrayList<>());
        return ipRes;
    }

    public static void ipProcess(String s, int index, List<String> path) {
        if (index == s.length() && path.size() == 4) {
            ipRes.add(String.join(".", path));
        }
        for (int i = index; i < s.length(); i++) {
            for (int j = i; j < i + 3 && j < s.length(); j++) {
                int value = Integer.parseInt(s.substring(i, j + 1));
                if (value > 255 || path.size() > 4) continue;
                path.add(String.valueOf(value));
                ipProcess(s, j + 1, path);
                path.remove(path.size() - 1);
            }
        }
    }

    public static void ipProcess3(String s, int index, List<String> path) {
        if (index == s.length() && path.size() == 4) {
            ipRes.add(String.join(".", path));
        }
        for (int i = index; i < s.length() && i - index < 3; i++) {
            if (i + 1 - index > 1 && s.charAt(index) == '0') continue;
            int value = Integer.parseInt(s.substring(index, i + 1));
            if (value > 255 || path.size() > 3) return;
            path.add(s.substring(index, i + 1));
            ipProcess3(s, i + 1, path);
            path.remove(path.size() - 1);
        }
    }


    public static void main(String[] args) {
        System.out.println(restoreIpAddresses93("101023"));
    }
}
