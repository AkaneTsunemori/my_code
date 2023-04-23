package a100;

public class Problem_0011_ContainerWithMostWater {
    public int maxArea(int[] height) {
        int left = 0;
        int rigth = height.length-1;
        int res = 0 ;

        while(left<rigth){
            int temp = (rigth-left) *Math.min(height[left],height[rigth]);
            if(res<temp){
                res = temp;
            }
            if(height[left]>height[rigth]){
                rigth--;
            }else{
                left++;
            }

        }
        return res;

    }
}
