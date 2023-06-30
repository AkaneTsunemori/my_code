package a100;

public class Problem_0005_LongestPalindromicSubstring {
    //    待测试
    public static void main(String[] args) {
        System.out.println(longestPalindrome("baabazabd"));
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int max = Integer.MIN_VALUE;
        int resC = 0;
        char[] manacherCharArray = getManacherCharArray(s);
        int C = -1;
        int R = -1;
        int[] pArr = new int[manacherCharArray.length];
        for (int i = 0; i < manacherCharArray.length; ++i) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < manacherCharArray.length && i - pArr[i] >= 0) {
                if (manacherCharArray[i + pArr[i]] == manacherCharArray[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            if (pArr[i] > max) {
                max = pArr[i];
                resC = C;
            }
        }
        return String.valueOf(manacherCharArray).substring(resC - max + 1, resC +max- 1).replace("#", "");
    }

    public static char[] getManacherCharArray(String s) {
        char[] chars = s.toCharArray();
        char[] resChars = new char[chars.length * 2 + 1];
        int index = 0;
        for (int i = 0; i < resChars.length; ++i) {
            resChars[i] = (i & 1) == 1 ? chars[index++] : '#';
        }
        return resChars;
    }

}
