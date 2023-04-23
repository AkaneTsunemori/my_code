package code.hot.c;

public  class a013_200_NumberOfIslands {
    public int numIslands(char[][] grid) {
        int res = 0;
        for(int i = 0;i<grid.length;i++){
            for(int j = 0;j<grid[0].length;++j){
                if(grid[i][j]=='1')res++;
                dfs(grid,i,j);
            }
        }
        return res;

    }
    public void dfs(char[][] grid,int row,int col){
        if(row<0||row==grid.length||col<0||col==grid[0].length||grid[row][col]=='0'){
            return;
        }
        grid[row][col]='0';
        dfs(grid,row,col-1);
        dfs(grid,row-1,col);
        dfs(grid,row+1,col);
        dfs(grid,row,col+1);
    }
}