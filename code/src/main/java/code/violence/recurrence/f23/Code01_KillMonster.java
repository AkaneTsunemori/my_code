package code.violence.recurrence.f23;

//样本对应模型

/**
 * 怪物血量blood, 勇士的最高伤害为damage,
 * 每次攻击为0~damage的等概率随机值,
 * 求attackTimes次杀死怪物的概率
 */
public class Code01_KillMonster {
    public static double right(int blood, int damage, int attackTimes) {
        if (blood < 1 || damage < 1 || attackTimes < 1) {
            return 0;
        }
        long all = (long) Math.pow(damage + 1, attackTimes);
        long kill = process(blood, damage, attackTimes);
        return (double) ((double) kill / (double) all);
    }

    private static long process(int blood, int damage, int rest) {
        if(rest==0){
            return blood<=0?1:0;
        }
        if(blood<=0){
            return (long)Math.pow(damage + 1, rest);
        }
        int res = 0;
        for (int i =0;i<=damage;++i){
            res+= process(blood-i,damage,rest-1);
        }
        return res;
    }
    public static double dp1(int blood, int damage, int rest) {
        if (blood < 1 || damage < 1 || rest < 1) {
            return 0;
        }
        long[][]dp = new long[blood+1][rest+1];
        dp[0][0] = 1;
        for (int i = 1;i<=rest;++i){
            dp[0][i] = (long)Math.pow(damage + 1, i);
        }
        for (int i = 1;i<=blood;++i){
            for (int j = 1;j<=rest;++j){
                for (int d = 0;d<=damage;++d){
                    if(i-d>=0){
                        dp[i][j]+=dp[i-d][j-1];
                    }else {
                        dp[i][j]+=dp[0][j-1];
                    }
                }
            }
        }
        long all = (long) Math.pow(damage + 1, rest);

        return (double) dp[blood][rest]/all;
    }

    public static double dp2(int blood, int damage, int rest) {
        if (blood < 1 || damage < 1 || rest < 1) {
            return 0;
        }
        long all = (long) Math.pow(damage + 1, rest);
        long[][] dp = new long[rest + 1][blood + 1];
        dp[0][0] = 1;
        for (int times = 1; times <= rest; times++) {
            dp[times][0] = (long) Math.pow(damage + 1, times);
            for (int hp = 1; hp <= blood; hp++) {
                dp[times][hp] = dp[times][hp - 1] + dp[times - 1][hp];
                if (hp - 1 - damage >= 0) {
                    dp[times][hp] -= dp[times - 1][hp - 1 - damage];
                } else {
                    dp[times][hp] -= Math.pow(damage + 1, times - 1);
                }
            }
        }
        long kill = dp[rest][blood];
        return (double) kill / (double) all;
    }
    public static void main(String[] args) {
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 200;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            double ans1 = right(N, M, K);
            double ans2 = dp1(N, M, K);
            double ans3 = dp2(N, M, K);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
