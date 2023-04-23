package code.violence.recurrence.b19;

public class Code02_CardsInLine {
    public static int win1(int[] arr){
        if(arr==null||arr.length==0){
            return 0;
        }
        int length = arr.length;
        int first = f1(arr,0,length-1);
        int second = g1(arr,0,length-1);
        return Math.max(first,second);
    }
    public static int f1(int[]arr, int l,int r){
        if(l==r){
            return arr[l];
        }else {
            return Math.max(arr[l]+g1(arr,l+1,r),arr[r]+g1(arr,l,r-1));
        }
    }
    public static int g1(int[]arr, int l,int r){
        if(l==r){
            return 0;
        }else {
            return Math.min(f1(arr,l+1,r),f1(arr,l,r-1));
        }
    }

    public static int win2(int[] arr){
        if(arr==null||arr.length==0){
            return 0;
        }
        int N = arr.length;

        int[][] dpf = new int[N][N];
        int[][] dpg = new int[N][N];
        for( int i = 0;i<N;++i){
            for(int j = 0;j<N;++j){
                dpf[i][j] = -1;
                dpg[i][j] = -1;
            }
            dpf[i][i] = arr[i];
            dpg[i][i] = 0;
        }


        int first = f2(arr,0,N-1,dpf,dpg);
        int second = g2(arr,0,N-1,dpf,dpg);
        return Math.max(first,second);
    }
    public static int f2(int[]arr, int l,int r, int[][] dpf,int[][]dpg){
        if(dpf[l][r]!=-1){
            return dpf[l][r];
        }else {
            dpf[l][r] =  Math.max(arr[l]+g2(arr,l+1,r,dpf,dpg),arr[r]+g2(arr,l,r-1,dpf,dpg));
            return dpf[l][r];
        }


    }
    public static int g2(int[]arr, int l,int r, int[][] dpf,int[][]dpg){
        if(dpg[l][r]!=-1){
            return dpg[l][r];
        }else {
            dpg[l][r] =  Math.min(f2(arr,l+1,r,dpf,dpg),f2(arr,l,r-1,dpf,dpg));
            return dpg[l][r];
        }
    }
    public static int win3(int[] arr){
        if(arr==null||arr.length==0){
            return 0;
        }
        int N = arr.length;

        int[][] dpf = new int[N][N];
        int[][] dpg = new int[N][N];
        for( int i = 0;i<N;++i){
            dpf[i][i] = arr[i];
            dpg[i][i] = 0;//多余的初始化, 方便回顾保留
        }
        for (int startCol = 1; startCol < N; startCol++) {
            int L = 0;
            int R = startCol;
            while (R < N) {
                dpf[L][R] = Math.max(arr[L] + dpg[L + 1][R], arr[R] + dpg[L][R - 1]);
                dpg[L][R] = Math.min(dpf[L + 1][R], dpf[L][R - 1]);
                L++;
                R++;
            }
        }
        return Math.max(dpf[0][N - 1], dpg[0][N - 1]);

    }
    public static int win2Dp(int[] arr) {
        int N = arr.length;
        int[][]dpf = new int[N][N];
        int[][]dps = new int[N][N];
        for(int i = 0;i<N;++i){
            dpf[i][i] = arr[i];
        }
//        L<=R
        for(int col = 1;col<N;++col){
            int j = col;
            int i = 0;
            while(j<N){
                dpf[i][j] = Math.max(arr[i]+dps[i+1][j],arr[j]+dps[i][j-1]);
                dps[i][j] = Math.min(dpf[i+1][j],dpf[i][j-1]);
                i++;
                j++;
            }

        }
        return Math.max(dpf[0][N-1],dps[0][N-1]);
    }
//    可以画出两个二维表, 以及上面f1,g1 ,f2,g2中的推导关系
    public static void main(String[] args) {
        int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));
        System.out.println(win2Dp(arr));

    }




























}
