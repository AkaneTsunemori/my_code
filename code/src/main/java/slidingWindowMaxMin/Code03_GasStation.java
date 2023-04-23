package slidingWindowMaxMin;

import java.util.Deque;
import java.util.LinkedList;

public class Code03_GasStation {
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int N = gas.length;
        int[]remain = new int[N];
        int res = 0;
        for(int i = 0;i<N;++i){
            remain[i] = gas[i]-cost[i];
        }
        int[] integrate = new int[2*N];
        int sum = 0;
        for(int i = 0;i<integrate.length;++i){
            int index = i>=N?i-N:i;
            sum+=remain[index];
            integrate[i] = sum;
        }
        int totalLength = integrate.length;

        int R = 0;
        Deque<Integer> dequeMin = new LinkedList<>();
        while(R<totalLength){
            while(!dequeMin.isEmpty()&&integrate[dequeMin.peekLast()]>=integrate[R]){
                dequeMin.pollLast();
            }
            dequeMin.add(R);
            if(R>=N-1){
                if(dequeMin.peekFirst()==R-N){
                    dequeMin.pollFirst();
                }
                int min = integrate[dequeMin.peekFirst()]-dequeMin.peekFirst()>1?integrate[dequeMin.peekFirst()-1]:0;
                if(min>=0){
                    res++;
                }
            }
            R++;
        }
        return res;
    }
}
