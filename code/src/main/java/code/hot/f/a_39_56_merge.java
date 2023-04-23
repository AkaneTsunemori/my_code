package code.hot.f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class a_39_56_merge {
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0]-o2[0]);
        List<Integer>res = new ArrayList<>();
        int lEdge = intervals[0][0];
        int rEdge = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if(lEdge<=intervals[i][0]&&intervals[i][0]<=rEdge){
                rEdge = Math.max(rEdge,intervals[i][1]);
            }else {
                res.add(lEdge);
                res.add(rEdge);
                lEdge = intervals[i][0];
                rEdge = intervals[i][1];
            }
        }
        res.add(lEdge);
        res.add(rEdge);
        int[][]resArray = new int[res.size()/2][2];
        for (int i = 0; i < res.size(); i++) {
            if((i&1)==0){
                resArray[i/2][0]=res.get(i);
            }else {
                resArray[i/2][1]=res.get(i);
            }
        }
        return resArray;

    }
    public static int[][] merge2(int[][] intervals) {
        Arrays.sort(intervals,(o1,o2)->o1[0]-o2[0]);
        List<Integer> resRc = new ArrayList<>();
        int left = intervals[0][0];
        int right = intervals[0][1];
        for (int i = 0; i < intervals.length-1; i++) {
            if(intervals[i+1][0]<=right&&right<intervals[i+1][1]){
                right = intervals[i+1][1];
            }else if(intervals[i+1][0]>right){
                resRc.add(left);
                resRc.add(right);
                left = intervals[i+1][0];
                right = intervals[i+1][1];
            }
        }
        resRc.add(left);
        resRc.add(right);
        int[][]res = new int[resRc.size()/2][2];
        for (int i = 0; i < resRc.size(); i++) {
            res[i/2][1&i] = resRc.get(i);
        }
        return res;


    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(merge(new int[][]{{1, 3}, {2, 6}, {8, 10}})));
        System.out.println(Arrays.deepToString(merge2(new int[][]{{1, 3}, {2, 6}, {8, 10}})));
        System.out.println(Arrays.deepToString(merge(new int[][]{{1, 4}, {2, 3}})));
        System.out.println(Arrays.deepToString(merge2(new int[][]{{1, 4}, {2, 3}})));


    }
}
