package code.hot.a;

/***
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
 *     请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。
 *
 * 题解类似动态规划: 保存上一次的滑动窗口左端位置 indexLeft, 以及当前char对应map中的位置 indexChar,
 * res = curIndex - Math.max(indexLeft,indexChar)
 */
public class a001_003_LongestSubStringWithoutRepeatingCharacters {
    public static int lengthOfLongestSubstring(String s){
        char[] chars = s.toCharArray();
        int[]map = new int[128];
        for(int i = 0;i<map.length;++i){
            map[i] = -1;
        }
        int res = 0;
        int left = -1;//表示上一次的滑动窗口的左端位置
        for(int i = 0;i!=chars.length;++i){
//            当前滑动窗口的左端位置应该是上次的左端位置和map中存的上一个char的index值取最大
            left = Math.max(left,map[chars[i]]);
            res = Math.max(res,i-left);
//            更新当前char的index
            map[chars[i]] = i;
        }
        return res;
    }
}
