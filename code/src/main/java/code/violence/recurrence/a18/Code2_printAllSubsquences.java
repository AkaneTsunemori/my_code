package code.violence.recurrence.a18;

import java.util.ArrayList;
import java.util.List;

public class Code2_printAllSubsquences {
    public static void main(String[] args) {
        sub("abc");
        Code2_printAllSubsquences code2_printAllSubsquences = new Code2_printAllSubsquences();
    }
    public static void sub(String s){
        char[] chars = s.toCharArray();
        //res使用set可以自动去重
        List<String>res = new ArrayList<>();
        process1(res,chars,0,"");
        res.forEach(System.out::println);
    }
    public static void process1(List<String>res, char[]chars,int index , String path){
        if(index==chars.length){
            res.add(path);
        }else {
            process1(res,chars,index+1,path);

            process1(res,chars,index+1,path+chars[index]);
        }
    }

}
