package code.hot.pdd;

import code.design.mode.struct.pattern.birdge.Test;
import code.hot.TreeNode;

import java.util.*;

public class Code {
    public static void main(String[] args) {
        Code code = new Code();
        System.out.println(code.longestOnes(new int[]{0, 0, 1, 1, 1, 0, 0}, 0));
        Integer a = 10;
        Integer b = 10;
        System.out.println(a.equals(b));
    }

    public int longestOnes(int[] nums, int k) {
        int res = 0;
        int sumR = 0;
        int sumL = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            sumR += 1 - nums[right];
            while (sumR - sumL > k) {
                sumL += 1 - nums[left++];
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    public int maxSubArray2(int[] nums) {
        int res = nums[0];
        int dp[] = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public int compareVersion(String version1, String version2) {
        int v1 = 0;
        int v2 = 0;
        while (v1 < version1.length() || v2 < version2.length()) {
            int v1Num = 0;
            int v2Num = 0;
            while (v1 < version1.length() && version1.charAt(v1) != '.') {
                v1Num = v1Num * 10 + (version1.charAt(v1++) - '0');
            }
            while (v2 < version2.length() && version2.charAt(v2) != '.') {
                v2Num = v2Num * 10 + (version2.charAt(v2++) - '0');
            }
            if (v1Num != v2Num) {
                return v1Num > v2Num ? 1 : -1;
            }
            v1++;
            v2++;

        }
        return 0;
    }

    public String decodeString(String s) {
        Stack<Integer> stackNum = new Stack<>();
        Stack<String> stackStr = new Stack<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; ) {
            if (chars[i] - '0' <= 9 && 0 <= chars[i] - '0') {
                int num = s.charAt(i) - '0';
                i++;
                while (i < chars.length && s.charAt(i) - '0' >= 0 && s.charAt(i) - '0' <= 9) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                stackNum.push(num);
            }
            if (i < chars.length && chars[i] == '[') {
                stackStr.push("[");
                i++;
            }
            if (i < chars.length && chars[i] - 'a' < 26 && 0 <= chars[i] - 'a') {
                int edge = i;
                while (i < chars.length && s.charAt(i) - 'a' < 26 && 0 <= s.charAt(i) - 'a') {
                    i++;
                }
                stackStr.push(s.substring(edge, i));
            }
            if (i < chars.length && chars[i] == ']') {
                String str = stackStr.pop();
                while (!stackStr.isEmpty() && !stackStr.peek().equals("[")) {
                    str = stackStr.pop() + str;
                }
                if (!stackStr.isEmpty() && stackStr.peek().equals("[")) {
                    stackStr.pop();
                }
                StringBuilder sb = new StringBuilder();
                if (!stackNum.isEmpty()) {
                    int num = stackNum.pop();
                    while (num-- > 0) {
                        sb.append(str);
                    }
                } else {
                    sb.append(str);
                }
                stackStr.push(sb.toString());
                i++;
            }
        }
        String res = "";
        while (!stackStr.isEmpty()) {
            res = stackStr.pop() + res;
        }


        return res;
    }


    public int findKthLargest(int[] nums, int k) {
        return processFindKthLargest(nums, 0, nums.length - 1, k);
    }

    public int processFindKthLargest(int[] nums, int l, int r, int k) {
        int index = partition(nums, l, r);
        if (index == k) {
            return nums[index];
        } else if (index < k) {
            return processFindKthLargest(nums, index + 1, r, k);
        } else {
            return processFindKthLargest(nums, l, index - 1, k);
        }
    }

    public int partitioin2(int[] nums, int l, int r) {
        int edge = l - 1;
        while (l < r) {
            if (nums[l] > nums[r]) {
                swap(nums, l, ++edge);
            }
            l++;
        }
        swap(nums, ++edge, r);
        return edge;
    }

    public int partition(int[] nums, int l, int r) {
        int edge = l - 1;
        while (l < r) {
            if (nums[l] > nums[r]) {
                swap(nums, l, ++edge);
            }
            l++;
        }
        //思考这里为什么要++
        swap(nums, r, ++edge);
        return edge;
    }

    public void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }

    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        List<Integer> resRc = new ArrayList<>();
        int left = intervals[0][0];
        int right = intervals[0][1];
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i + 1][0] <= right && right < intervals[i + 1][i]) {
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

    // * 输入: s = "pwwkew"
    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int[] map = new int[128];
        Arrays.fill(map, -1);
        int res = 0;
        int left = -1;
        for (int i = 0; i < chars.length; i++) {
            left = Math.max(left, map[chars[i]]);
            res = Math.max(res, i - left);
            map[chars[i]] = i;
        }
        return res;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int left = 0;
        int right = col - 1;
        int top = 0;
        int button = row - 1;
        List<Integer> res = new ArrayList<>();

        while (left < right && top < button) {
            for (int i = left; i < right; ++i) {
                res.add(matrix[top][i]);
            }
            for (int i = top; i < button; ++i) {
                res.add(matrix[i][right]);
            }
            for (int i = right; i > left; --i) {
                res.add(matrix[button][i]);
            }
            for (int i = button; i > top; --i) {
                res.add(matrix[i][left]);
            }
            left++;
            right--;
            top++;
            button--;
        }
        if (left > right || top < button) {
            return res;
        }
        if (left == right && top == button) {
            res.add(matrix[left][top]);
        } else if (left == right) {
            for (int i = top; i >= button; ++i) {
                res.add(matrix[i][left]);
            }
        } else {
            for (int i = left; i <= right; ++i) {
                res.add(matrix[top][i]);
            }
        }
        return res;
    }

    public int longestValidParentheses(String s) {
        char[] chars = s.toCharArray();
        int resL = 0;
        int resR = 0;
        int leftCount = 0;
        int rightCount = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                leftCount++;
            } else {
                rightCount++;
            }
            if (rightCount > leftCount) {
                leftCount = 0;
                rightCount = 0;
                continue;
            }
            if (rightCount == leftCount) {
                resL = Math.max(resL, rightCount);
            }
        }
        leftCount = 0;
        rightCount = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == ')') {
                rightCount++;
            } else {
                leftCount++;
            }
            if (rightCount < leftCount) {
                leftCount = 0;
                rightCount = 0;
                continue;
            }
            if (rightCount == leftCount) {
                resR = Math.max(resR, rightCount);
            }
        }
        return Math.max(resL, resR) * 2;
    }

    public int maxProfit(int[] prices) {
        int min = prices[0];
        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            res = Math.max(res, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return res;
    }

    public static int minDistance(String word1, String word2) {
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        int M = chars1.length;
        int N = chars2.length;
        int[][] dp = new int[M + 1][N + 1];
        dp[0][0] = 0;
        for (int i = 0; i <= M; ++i) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= N; ++i) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= M; ++i) {
            for (int j = 1; j <= N; j++) {
                int one = dp[i - 1][j] + 1;
                int two = dp[i][j - 1] + 1;
                int three = chars1[i - 1] == chars2[j - 1] ? dp[i - 1][j - 1] : dp[i - 1][j - 1] + 1;
                dp[i][j] = Math.min(Math.min(one, two), three);
            }
        }
        return dp[M][N];
    }

    public String longestPalindrome(String s) {
        char[] chars = makePalindromeChars(s);
        int len = chars.length;
        int[] arr = new int[len];
        int resC = -1;
        int resL = -1;
        int C = -1;
        int R = -1;
        for (int i = 0; i < chars.length; i++) {
            arr[i] = i >= R ? 1 : Math.min(arr[2 * C - i], R - i);
            while (i - arr[i] >= 0 && i + arr[i] < chars.length && chars[i - arr[i]] == chars[i + arr[i]]) {
                arr[i]++;
            }
            if (arr[i] + i > R) {
                R = arr[i] + i;
                C = i;
            }
            if (arr[i] > resL) {
                resL = arr[i];
                resC = i;
            }
        }
        return String.valueOf(chars).substring(resC - resL + 1, resC + resL).replace("#", "");
    }

    public char[] makePalindromeChars(String s) {
        char[] chars = s.toCharArray();
        char[] res = new char[chars.length * 2 + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : chars[i / 2];
        }
        return res;
    }


}
