package a100;

public class Problem_0007_ReverseInteger {
    public static int reverse(int x) {
        int res = 0;
        while(x!=0){
            int digit = x%10;
            x = x/10;
            if(res<Integer.MIN_VALUE/10||res>Integer.MAX_VALUE/10)return 0;
            res = res*10+digit;
        }
        return res;
    }

    public static int reverse2(int x){
        boolean neg = ((x>>>31)&1)==1;
        x = neg?x:-x;

        int m = Integer.MIN_VALUE/10;
        int o = Integer.MIN_VALUE%10;
        int res = 0;
        while(x!=0){
            if(res<m||(res==m&&x%10<o)){
                return 0;
            }
            res = res*10+x%10;
            x/=10;
        }
        return neg?res:-res;
    }
    public static void main(String[] args) {
        System.out.println(reverse(-110));
        System.out.println(-11%10);
    }
}
