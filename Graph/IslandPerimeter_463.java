package Graph;

/**
 * 求出岛屿的周长
 * 方法一：正方形格子的边算作周长有两种情况，一种是边界，一种是旁边的格子是河流，
 *       所以只要遍历每个格子看它的四周有几条边属于这些情况，所有加起来就是周长
 * 方法二：利用DFS，当超出边界时，周长加1，当遇到河流时，周长加1，对于已遍历的陆地格子，周长不变
 */
public class IslandPerimeter_463 {
    public int islandPerimeter(int[][] grid) {
        if(grid==null||grid.length==0){
            return 0;
        }
        int[] x={0,1,0,-1};
        int[] y={1,0,-1,0};
        int count=0;
        int m=grid.length;
        int n=grid[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1){
                    for(int k=0;k<4;k++){
                        int nx=i+x[k];
                        int ny=j+y[k];
                        if(nx<0||ny<0||nx>=m||ny>=n||grid[nx][ny]==0){
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }


    public int islandPerimeterByDFS(int[][] grid) {
        if(grid==null||grid.length==0){
            return 0;
        }
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    return dfs(grid,i,j);
                }
            }
        }
        return 0;
    }
    private int dfs(int[][] grid, int i, int j) {
        if(!isValid(grid,i,j)){
            return 1;
        }
        if(grid[i][j]==0){
            return 1;
        }
        //函数因为「当前格子是已遍历的陆地格子」返回，和周长没关系
        if(grid[i][j]!=1){
            return 0;
        }
        //表示已访问过
        grid[i][j]=2;
        return dfs(grid,i,j+1)+dfs(grid,i+1,j)+dfs(grid,i,j-1)+dfs(grid,i-1,j);
    }
    private boolean isValid(int[][] grid, int i, int j) {
        return i>=0&&i<grid.length&&j>=0&&j<grid[0].length;
    }

}
