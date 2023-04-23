package code.violence.recurrence.a18;

public class Code1_hanoi {
    public static void main(String[] args) {
        hanoi(4);

    }
    public static void hanoi(int N ){
        func(N,"left","right","mid");

    }
    public static void func(int N, String l, String r, String m){
        if(N==1){
            System.out.println("move "+1+" from "+l+" to "+ r);
        }else{
            func(N-1,l,m,r);
            System.out.println("move "+N+" from "+l+" to "+ r);
            func(N-1,m,r,l);
        }

    }

}
