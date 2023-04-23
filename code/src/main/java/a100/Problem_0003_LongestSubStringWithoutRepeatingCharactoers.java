package a100;

public class Problem_0003_LongestSubStringWithoutRepeatingCharactoers {
    public static int lengthOfLongestSubstring(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int left = -1;
        int res = 0;
        int[] map = new int[128];
        for (int i = 0; i < map.length; i++) {
            map[i] = -1;
        }
        for (int i = 0; i < chars.length; ++i) {
            left = Math.max(left, map[chars[i]]);
            map[chars[i]] = i;
            res = Math.max(res, i - left);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("a"));
    }

}
