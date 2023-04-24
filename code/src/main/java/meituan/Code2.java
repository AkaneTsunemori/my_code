package meituan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code2 {
    class Solution21 {
        public String longestPalindrome(String s) {
            char[] charArray = getCharArray(s);
            int R = -1;
            int C = -1;
            int resC = -1;
            int max = -1;

            int[] pArr = new int[charArray.length];
            for (int i = 0; i < charArray.length; i++) {
                pArr[i] = R - i > 0 ? Math.min(pArr[2 * C - i], R - i) : 1;
                while (i + pArr[i] < charArray.length && i - pArr[i] >= 0 && charArray[i + pArr[i]] == charArray[i - pArr[i]]) {
                    pArr[i]++;
                }
                if (pArr[i] > max) {
                    max = pArr[i];
                    resC = i;
                }
                if (pArr[i] + i > R) {
                    R = pArr[i] + i;
                    C = i;
                }
            }
            return String.valueOf(charArray).substring(resC - max + 1, resC + max).replace("#", "");

        }

        public char[] getCharArray(String s) {
            char[] res = new char[s.length() * 2 + 1];
            for (int i = 0; i < res.length; i++) {
                res[i] = (i & 1) == 0 ? '#' : s.charAt(i / 2);
            }
            return res;
        }
    }

    //输入：s = "101023"
////输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
    class Solution22 {
        List<String> res = new ArrayList<>();

        public List<String> restoreIpAddresses(String s) {
            ipProcess(s, 0, new ArrayList<>());
            return res;
        }

        public void ipProcess(String s, int index, List<String> path) {

            if (index == s.length() && path.size() == 4) {
                res.add(String.join(".", path));
            }else {
                for (int i = index + 1; i <= s.length()&&i-index<=3; ++i) {

                    if(i-index>=2&&s.charAt(index)=='0'){
                        continue;
                    }
                    if(i-index==3&&Integer.parseInt(s.substring(index,i))>255){
                        continue;
                    }

                    path.add(s.substring(index, i));
                    ipProcess(s, i, path);
                    path.remove(path.size() - 1);
                }
            }
        }
    }
    //给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
    class Solution23 {
        public int findLength(int[] nums1, int[] nums2) {
            int M = nums1.length;
            int N = nums2.length;
            int res = 0;
            int[][]dp = new int[M+1][N+1];
            for(int i = 1;i<=M;++i){
                for(int j = 1;j<=N;++j){
                    dp[i][j] = nums1[i-1]==nums2[j-1]?dp[i-1][j-1]+1:0;
                    res = Math.max(res,dp[i][j]);
                }
            }
            return res;
        }
    }
    class Solution24 {
        public List<Integer> spiralOrder(int[][] matrix) {
            int rowB = matrix.length-1;
            int colR = matrix[0].length-1;
            List<Integer> res = new ArrayList<>();

            int rowT = 0;
            int colL = 0;
            while(colL<=colR&&rowT<=rowB){
                for (int i = colL; i <= colR; i++) {
                    res.add(matrix[rowT][i]);
                }
                for (int i = rowT+1; i <= rowB; i++) {
                    res.add(matrix[colR][i]);
                }
                for (int i = colR-1; i >= colL; i--) {
                    res.add(matrix[rowB][i]);
                }
                for (int i = rowB-1; i >=rowT-1; i--) {
                    res.add(matrix[rowT][i]);
                }
                colL++;
                colR--;
                rowT++;
                rowB--;
            }
            return res;
        }
    }
}

