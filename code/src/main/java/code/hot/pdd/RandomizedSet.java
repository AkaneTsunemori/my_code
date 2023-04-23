package code.hot.pdd;

import java.util.*;

public class RandomizedSet {
    List<Integer>list;
    Map<Integer,Integer> map;

    public RandomizedSet() {

        list = new ArrayList<>();
        map = new HashMap<>();
    }

    public boolean insert(int val) {
        if(map.containsKey(val)){
            return false;
        }else {
            list.add(val);
            map.put(val,list.size()-1);
            return true;
        }
    }

    public boolean remove(int val) {
        if(!map.containsKey(val)){
            return false;
        }else {
            Integer index = map.get(val);
            Integer last = list.get(list.size() - 1);
            list.set(index,last);
            map.put(last,index);
            list.remove(list.size()-1);
            map.remove(val);
            return true;
        }

    }

    public int getRandom() {
        return list.get((int) (Math.random() * list.size()));
    }

}
