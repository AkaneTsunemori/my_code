package a100;

import java.util.Arrays;

public class Problem_0003_LongestSubStringWithoutRepeatingCharactoers {
    public static int lengthOfLongestSubstring(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int left = -1;
        int res = 0;
        int[] map = new int[128];
        Arrays.fill(map, -1);
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            left = Math.max(left, map[chars[i]]);
            map[chars[i]] = i;
            res = Math.max(res, i - left);
        }
        return res;
    }

}
