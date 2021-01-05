package Graph;

/**
 * 给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围
 */
public class NumberOfIslands_200 {
    private int m;
    private int n;
    public int numIslands(char[][] grid) {
        if(grid==null||grid.length==0) {
            return 0;
        }
        m=grid.length;
        n=grid[0].length;
        int count = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]!='0'){
                    count++;
                    dfs(grid,i,j);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        grid[i][j]='0';
        if(j<n-1&&grid[i][j+1]!='0'){
            dfs(grid,i,j+1);
        }
        if(i<m-1&&grid[i+1][j]!='0'){
            dfs(grid,i+1,j);
        }
        if(i>0&&grid[i-1][j]!='0'){
            dfs(grid,i-1,j);
        }
        if(j>0&&grid[i][j-1]!='0'){
            dfs(grid,i,j-1);
        }
    }
}
