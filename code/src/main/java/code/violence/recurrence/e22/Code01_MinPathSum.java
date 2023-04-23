package code.violence.recurrence.e22;

public class Code01_MinPathSum {
    public static int minPathSum1(int[][] matrix) {
        return process(matrix,0,0);
    }
    public static int process(int[][]matrix,int curR,int curC){
        if(curR==matrix.length-1&&curC==matrix[0].length-1){
            return matrix[curR][curC];
        }
        if(curR==matrix.length-1){
            return process(matrix,curR,curC+1)+matrix[curR][curC];
        }
        if(curC==matrix[0].length-1){
            return process(matrix,curR+1,curC)+matrix[curR][curC];
        }
        return Math.min(process(matrix,curR+1,curC),process(matrix,curR,curC+1))+matrix[curR][curC];
    }
    public static int minPathSumDp(int[][] matrix) {
        int R = matrix.length;
        int C = matrix[0].length;
        int[][]dp = new int[R][C];
        dp[R-1][C-1] = matrix[R-1][C-1];
        for (int i = C-2;i>=0;--i){
            dp[R-1][i] = dp[R-1][i+1]+matrix[R-1][i];
        }
        for (int i = R-2;i>=0;--i){
            dp[i][C-1] = dp[i+1][C-1]+matrix[i][C-1];
        }
        for (int i = R-2;i>=0;--i){
            for(int j =C-2;j>=0;--j){
                dp[i][j] = Math.min(dp[i+1][j],dp[i][j+1])+matrix[i][j];
            }
        }
        return dp[0][0];
    }
    public static int minPathSumDp2(int[][] matrix) {
        int R = matrix.length;
        int C = matrix[0].length;
        int[]dp = new int[R];
        dp[0] = matrix[0][0];
        for(int i = 1;i<R;++i){
            dp[i] = dp[i-1]+matrix[0][i];
        }
        for (int j =1;j<C;++j){
            dp[0] = dp[0]+matrix[j][0];
            for (int i = 1;i<R;++i){
                dp[i] = Math.min(dp[i],dp[i-1])+matrix[j][i];
            }
        }
        return dp[R-1];
    }

        public static int minPathSum2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 100);
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int rowSize = 10;
        int colSize = 10;
        int[][] m = generateRandomMatrix(rowSize, colSize);
        System.out.println(minPathSum1(m));
        System.out.println(minPathSum2(m));
        System.out.println(minPathSumDp(m));
        System.out.println(minPathSumDp2(m));


    }

}
