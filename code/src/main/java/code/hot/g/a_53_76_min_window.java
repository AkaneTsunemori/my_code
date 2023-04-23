package code.hot.g;

import java.util.HashMap;
import java.util.Map;

/**
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 */
public class a_53_76_min_window {
    public static  String minWindow(String s, String t) {
        String res = "";
        int len = Integer.MAX_VALUE;
        Map<Character, Integer> sourceMap = new HashMap<>();
        Map<Character, Integer> targetMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            targetMap.put(t.charAt(i), targetMap.getOrDefault(t.charAt(i), 0) + 1);
        }
        int l = 0, r = 0;
        while (r < s.length()) {
            while (r < s.length() && !check(sourceMap, targetMap)) {
                sourceMap.put(s.charAt(r), sourceMap.getOrDefault(s.charAt(r), 0) + 1);
                r++;
            }

            while (check(sourceMap, targetMap)) {
                Integer sourceLen = sourceMap.values().stream().reduce(Integer::sum).get();
                if (sourceLen< len) {
                    res = s.substring(l, r );
                    len = sourceLen;
                }
                sourceMap.put(s.charAt(l), sourceMap.get(s.charAt(l)) - 1);
                l++;
            }

        }
        return res;
    }

    public static  boolean check(Map<Character, Integer> sourceMap, Map<Character, Integer> targetMap) {
        for (Character c : targetMap.keySet()) {
            if (!sourceMap.containsKey(c) || sourceMap.get(c) < targetMap.get(c)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC",
                "ABC"));
        System.out.println(minWindow("bba",
                "ab"));
    }


}
