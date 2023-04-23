package a100;

public class Problem_0010_RegularExpressionMatching {
    public static boolean isMatch1(String s, String p) {
        if (s == null || p == null) return false;
        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();
        return isValid(str, pattern) && process(str, pattern, 0, 0);
    }
    //有效性检查
    public static boolean isValid(char[] str, char[] pattern) {
        for (char c : str) {
            if (c == '.' || c == '*') {
                return false;
            }
        }

        for (int i = 0; i < pattern.length; i++) {
            if ((i == 0 && pattern[i] == '*') || (i > 0 && pattern[i] == '*' && pattern[i - 1] == '*')) {
                return false;
            }
//            与上面的写法等价
//            if(pattern[i]=='*'&&(i==0||pattern[i-1]=='*')){
//                return false;
//            }
        }
        return true;
    }

    //str[si ... ] 能否被pattern[pi ... ]匹配出来
    //不接受pattern[pi]以'*'开头的子过程, 即: pi位置, pattern[pi]!='*'
    public static boolean process(char[] str, char[] pattern, int si, int pi) {
        if (si == str.length) {//str[ si ...]  :""
            //pi也来到最后一个位置
            if (pi == pattern.length) {
                return true;
            }
            if (pi + 1 < pattern.length && pattern[pi + 1] == '*') {
                // 因为有效性检查不会出现连续的两个** 所有pi+2位置不以* 开头
                return process(str, pattern, si, pi + 2);
            }
            return false;
        }
        if (pi == pattern.length) {
            return false;
        }
        //si和pi都没有终止
        //保证下一个位置没有'*'
        if (pi + 1 == pattern.length || pattern[pi + 1] != '*') {
            if (str[si] == pattern[pi] || pattern[pi] == '.') {
                return process(str, pattern, si + 1, pi + 1);
            }else return false;
        }
        //pi+1为*
        //如果pi*匹配零个字符
        if (process(str, pattern, si, pi + 2)) {
            return true;
        }
        while (si < str.length && (str[si] == pattern[pi] || pattern[pi] == '.')) {
            if (process(str, pattern, si+1, pi + 2)) {
                return true;
            }
            si++;
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(isMatch1("aab",
                "c*a*b*.*"));
    }
}
