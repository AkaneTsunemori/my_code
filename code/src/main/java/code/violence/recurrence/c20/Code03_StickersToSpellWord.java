package code.violence.recurrence.c20;

import java.util.HashMap;
import java.util.Map;

/**
 * 本题改动态规划:
 * 可变参数为target, 因为string类型不存在严格的表结构, 所以只能用map来做缓存
 */
public class Code03_StickersToSpellWord {
    public static int minStickers1(String[] stickers, String target) {
        int ans = process1(stickers, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;

    }

    public static int process1(String[] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }
//        求同层所有条件中的最小值, 先给定一个最大, 然后求所有的min
        int min = Integer.MAX_VALUE;
        for (String sticker : stickers) {
            String rest = minus(target, sticker);
            if (rest.length() != target.length()) {
                min = Math.min(min, process1(stickers, rest));
            }
        }
        return min == Integer.MAX_VALUE ? min : min + 1;
    }

    public static String minus(String target, String sticker) {
        char[] charsTarget = target.toCharArray();
        char[] charsSticker = sticker.toCharArray();
        int[] count = new int[26];
        for (char c : charsTarget) {
            count[c - 'a']++;
        }
        for (char c : charsSticker) {
            count[c - 'a']--;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; ++i) {
            if (count[i] > 0) {
                for (int j = 0; j < count[i]; ++j) {
                    sb.append((char) (i + 'a'));
                }
            }
        }
        return sb.toString();
    }

    public static int minStickers2(String[] stickers, String target) {
        int N = stickers.length;
        int[][] counts = new int[N][26];
        for (int i = 0; i < stickers.length; i++) {
            char[] chars = stickers[i].toCharArray();
            for (char aChar : chars) {
                counts[i][aChar - 'a']++;
            }
        }
        int ans = process2(counts, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;

    }

    public static int process2(int[][] counts, String target) {
        if (target.length() == 0) {
            return 0;
        }
        char[] charsTarget = target.toCharArray();
        int[] countT = new int[26];
        for (char c : charsTarget) {
            countT[c - 'a']++;
        }
        int min = Integer.MAX_VALUE;

        for (int[] count : counts) {
//剪枝,
            if (count[charsTarget[0] - 'a'] > 0) {
                for (int i = 0; i < 26; ++i) {
                    if (count[i] > 0) {
                        charsTarget[i]--;
                    }
                }
                StringBuilder rest = new StringBuilder();
                for (int i = 0; i < 26; ++i) {
                    for (int j = charsTarget[i]; j > 0; --j) {
                        rest.append('a' + i);
                    }
                }
                min = Math.min(min, process2(counts, rest.toString()));
            }
        }
        return min == Integer.MAX_VALUE ? min : min + 1;
    }

    public static int minStickers3(String[] stickers, String target) {
        int N = stickers.length;
        int[][] counts = new int[N][26];
        for (int i = 0; i < stickers.length; i++) {
            char[] chars = stickers[i].toCharArray();
            for (char aChar : chars) {
                counts[i][aChar - 'a']++;
            }
        }
        Map<String, Integer> cache = new HashMap<>();
        int ans = process3(counts, target, cache);

        return ans == Integer.MAX_VALUE ? -1 : ans;

    }

    public static int process3(int[][] counts, String target, Map<String, Integer> cache) {
        if (target.length() == 0) {
            return 0;
        }
        if (cache.containsKey(target)) {
            return cache.get(target);
        }
        char[] charsTarget = target.toCharArray();
        int[] countT = new int[26];
        for (char c : charsTarget) {
            countT[c - 'a']++;
        }
        int min = Integer.MAX_VALUE;

        for (int[] count : counts) {
//剪枝,
            if (count[charsTarget[0] - 'a'] > 0) {
                for (int i = 0; i < 26; ++i) {
                    if (count[i] > 0) {
                        charsTarget[i]--;
                    }
                }
                StringBuilder rest = new StringBuilder();
                for (int i = 0; i < 26; ++i) {
                    for (int j = charsTarget[i]; j > 0; --j) {
                        rest.append('a' + i);
                    }
                }
                min = Math.min(min, process2(counts, rest.toString()));
            }
        }
        int res = min == Integer.MAX_VALUE ? min : min + 1;
        cache.put(target, res);
        return res;
    }

    public static void main(String[] args) {
    }



    public static int minStickers4(String[] stickers, String target) {
        int N = stickers.length;
        int[][]counts = new int[N][26];
        for (int i = 0; i < stickers.length; i++) {
            char[] charSticker = stickers[i].toCharArray();
            for (char c : charSticker) {
                counts[i][c-'a']++;
            }
        }

        return  processMinStickers1(counts,target);


    }
    public static int processMinStickers1(int[][]counts,String target){
        if(target.equals("")){
            return 0;
        }
        int res = Integer.MAX_VALUE;
        for (int[] count : counts) {
            String minusString = arrayMinus(count,target);
            if(minusString.length()!=target.length()){
                res = processMinStickers1(counts,minusString)+1;
            }
        }
        return Math.min(Integer.MAX_VALUE,res);
    }
    public static String arrayMinus(int[]count, String target){
        int[]targetCount = new int[26];
        char[] targetChars = target.toCharArray();
        for (char targetChar : targetChars) {
            targetCount[targetChar-'a']++;
        }
        for (int i = 0; i < count.length; i++) {
            targetCount[i] = targetCount[i]-count[i];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i<26;++i) {
            while(targetCount[i]>0){
                sb.append((char)('a'+i));
            }
        }
        return sb.toString();
    }



}
