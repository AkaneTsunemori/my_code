package a100;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int days = scanner.nextInt();
        int[] nums = new int[number];
        int[][]prices = new int[number][days];
        for (int i = 0; i < number; i++) {
            nums[i]=scanner.nextInt();
        }
        for(int i = 0;i<number;++i){
            for (int j = 0;j<days;++j){
                prices[i][j] = scanner.nextInt();
            }
        }
        int sum = 0;
        for (int i = 0; i < prices.length; i++) {
            sum+=getValue(prices[i])*nums[i];
        }
        System.out.println(sum);

    }
    public static int getValue(int[] arr){
        int res = 0;
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            min = Math.min(min,arr[i]);
            res = Math.max(res,arr[i]-min);
        }
        return res;
    }
}
