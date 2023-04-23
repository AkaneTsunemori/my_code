package code.violence.recurrence.d21;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Code03_Coffee {

    public static int bestTime(int[]drinks,int wash,int air,int index,int washTime){
        if(index==drinks.length){
            return 0;
        }
        int timeAir = Math.max(Math.max(drinks[index]+air,washTime),bestTime(drinks,wash,air,index+1,washTime));
        int timeWash = Math.max(Math.max(drinks[index],washTime)+wash,bestTime(drinks,wash,air,index+1,Math.max(drinks[index],washTime)+wash));
        return Math.min(timeWash,timeAir);
    }

    public static int minTime1(int[] arr, int n, int wash, int air) {
        if(arr==null|| arr.length==0){
            return 0;
        }
        Queue<Machine> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.drinkTime));
        for (int i : arr) {
            queue.add(new Machine(0, i));
        }
        int[] drinks = new int[n];
        for (int i = 0; i < arr.length; i++) {
            Machine curMachine = queue.poll();
            drinks[i] = curMachine.drinkTime;
            curMachine.setUseTime(curMachine.drinkTime);
        }
        return bestTime(drinks,wash,air,0,0);
    }

    public static class Machine {
        public Machine(int useTime, int cdTime) {
            this.useTime = useTime;
            this.cdTime = cdTime;
            this.drinkTime = useTime+cdTime;
        }

        int useTime;
        int cdTime;
        int drinkTime;

        public void setUseTime(int useTime) {
            this.useTime = useTime;
            this.drinkTime = useTime+cdTime;
        }
    }

}
