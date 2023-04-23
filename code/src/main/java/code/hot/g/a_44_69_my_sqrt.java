package code.hot.g;


public class a_44_69_my_sqrt {
    public  int mySqrt(int x) {
        int l = 0;
        int r = x;
        int mid;
        int res = 0;
        while (l <= r) {
            mid = l + ((r - l) >> 1);
            if ((long) mid * mid <= x) {
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return res;
    }
}
