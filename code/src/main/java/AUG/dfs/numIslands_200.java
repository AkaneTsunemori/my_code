package AUG.dfs;

public class numIslands_200 {
    public int numIslands(char[][] grid) {
        int width = grid.length;
        int high = grid[0].length;
        int result = 0;
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < high; ++j) {
                if (grid[i][j] == '1') {
                    process(grid, i, j);
                    result++;
                }
            }
        }
        return result;
    }

    public void process(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        process(grid, i - 1, j);
        process(grid, i + 1, j);
        process(grid, i, j - 1);
        process(grid, i, j + 1);
    }
}
