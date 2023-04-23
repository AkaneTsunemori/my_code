package a100;


/**
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 *
 *
 */
public class Problem_0014_LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        char[] chs = strs[0].toCharArray();
        int min = Integer.MAX_VALUE;
        for (String str : strs) {
            char[] tmp = str.toCharArray();
            int index = 0;
            while (index < tmp.length && index < min) {
                if (chs[index] != tmp[index]) {
                    break;
                }
                index++;
            }
            min = Math.min(index, min);
            if (min == 0) {
                return "";
            }
        }
        return strs[0].substring(0, min);
    }
    public static String longestCommonPrefix2(String[] strs) {
        String first = strs[0];
        int index = first.length();
        for (String str : strs) {
            for (int i = 0;i<Math.min(index,str.length());++i){
                if(str.charAt(i)!=first.charAt(i)){
                    index = i;
                    break;
                }
            }
        }
        return first.substring(0,index);
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix2(new String[]{"flower", "flow", "flight"}));
        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"}));

    }
}
