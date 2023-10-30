import code.hot.TreeNode;

import java.util.*;

class Solution {

    Queue<Pair>queue = new PriorityQueue<>((o1,o2)->
            o2.count-o1.count
    );

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer>map = new HashMap<>();
        List<Integer>list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        for (Map.Entry<Integer,Integer>entry:map.entrySet()){
            queue.add(new Pair(entry.getKey(),entry.getValue()));
        }
        while(!queue.isEmpty()&&queue.peek().count>k){
            list.add(queue.poll().value);
        }
        int[]rst = new int[list.size()];
        for (int i = 0; i < rst.length; i++) {
            rst[i] = list.get(i);
        }
        return rst;
    }
    public static boolean checkDynasty(int[] places) {
        Arrays.sort(places);
        int len = places.length;
        int i = 0;
        for(;i<len;++i){
            if(places[i]!=0){
                break;
            }
        }
        return places[len-1]-places[i]-i+len-1<=i;
    }

    public static void main(String[] args) {
        System.out.println(checkDynasty(new int[]{0, 0, 6, 9, 7}));
    }
}
class Pair{
    int value;
    int count;
    Pair(int value, int count ){
        this.value = value;
        this.count = count;
    }
}