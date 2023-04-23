package code.violence.recurrence.c20;

public class Code02_ConvertToLetterString {
    public static int number(String str){
        if(str==null||str.length()==0){
            return 0;
        }
        return process(str.toCharArray(),0);
    }
    public static int process(char[]chars,int index){
        if(index==chars.length){
            return 1;
        }
        if(chars[index]=='0'){
            return 0;
        }
//        让index位置单转
        int ways = process(chars,index+1);
        if(index+1<chars.length&&((chars[index]-'0')*10+(chars[index+1]-'0')<27)){
            ways += process(chars,index+2);
        }
        return ways;

    }
    public static int dp(String str){
        if(str==null||str.length()==0){
            return 0;
        }
        char[]chars = str.toCharArray();
        int N = chars.length;
        int[]dp = new int[N +1];
        dp[N] = 1;
        for(int i = N-1;i>=0;--i){
            if(chars[i]=='0'){
                dp[i] = 0;
            }
            dp[i] = process(chars,i+1);
            if(i+1<chars.length&&((chars[i]-'0')*10+(chars[i+1]-'0')<27)){
                dp[i] += process(chars,i+2);
            }
        }
        return dp[0];

    }
    public static int convertToLetterString(String str) {
        char[] chars = str.toCharArray();
        return processConvertToLetterString(chars, 0);
    }

    public static int processConvertToLetterString(char[] chars, int index) {
        if (index == chars.length) {
            return 1;
        }
        if (chars[index] == '0') return 0;
        int p1 = processConvertToLetterString(chars, index + 1);
        int p2 = 0;
        if(chars.length-index>=2&& (chars[index]-'0')*10+chars[index+1]-'0'<27){
            p2 = processConvertToLetterString(chars,index+2);
        }
        return p1+p2;
    }
    public static int convertToLetterStringDp(String str){
        char[] chars = str.toCharArray();
        int N = chars.length;
        int[] dp = new int[N+1];
        dp[N]=1;
        for(int i = N-1;i>=0;--i){
            if(chars[i]=='0') dp[i] = 0;
            int p1 = dp[i + 1];
            int p2 = 0;
            if(chars.length-i>=2&& (chars[i]-'0')*10+chars[i+1]-'0'<27){
                p2 = dp[i+2];
            }
            dp[i] = p1+p2;

        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(number("23426314141453"));
        System.out.println(dp("23426314141453"));
        System.out.println(convertToLetterString("23426314141453"));
        System.out.println(convertToLetterStringDp("23426314141453"));


    }
}
