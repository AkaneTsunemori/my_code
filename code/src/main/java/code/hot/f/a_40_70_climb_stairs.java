package code.hot.f;

public class a_40_70_climb_stairs {
    public static  int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public static int climbStairs2(int n) {
        if (n == 1) return n;
        int first = 1;
        int second = 2;
        int res = second;
        while(n-->2){
            res = second+first;
            first = second;
            second = res;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(10));
        System.out.println(climbStairs2(10));
    }
}
