package code.violence.recurrence.a18;

import java.util.ArrayList;
import java.util.List;

public class Code03_PrintAllPermutations {
    public static void main(String[] args) {
        permutation1("aac");
    }


    public static void  permutation1(String s) {
        char[] chars = s.toCharArray();
        List<String>res = new ArrayList<>();
        process2(res,chars,0);
        res.forEach(System.out::println);

    }
    public static void process1(List<String>res, char[] chars,int index ){
        if(index==chars.length-1){
            res.add(new String(chars));
        }else{
            for (int i= index;i<chars.length;++i){
                swap(chars,index,i);
                process1(res, chars,index+1);
                swap(chars,index,i);


            }
        }
    }
    public static void process2(List<String>res, char[] chars,int index ){
        if(index==chars.length-1){
            res.add(new String(chars));
        }else{
            boolean[] visites = new boolean[255];
            for (int i= index;i<chars.length;++i){
                if(!visites[chars[i]]){
                    visites[chars[i]] = true;
                    swap(chars,index,i);
                    process2(res, chars,index+1);
                    swap(chars,index,i);
                }
            }
        }
    }

    public static  void swap(char[] chars, int m ,int n ){
        char temp = chars[m];
        chars[m] = chars[n];
        chars[n] = temp;
    }
}
